package br.com.luizalabs.desafio.cep.service.impl;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.core.entity.Cep;
import br.com.luizalabs.desafio.cep.core.enums.StateDetail;
import br.com.luizalabs.desafio.cep.core.exception.InvalidCepException;
import br.com.luizalabs.desafio.cep.core.repository.CepRepository;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class CepServiceImpl implements CepService {

    private final CepRepository cepRepository;

    @Override
    public CepDTO findCep(String cep) {
        Cep cepEntity;

        try {
            log.info("STEP -> Execute findByIdWithLeftpad (database ceps may have 7 digits)");
            Optional<Cep> cepOptional = cepRepository.findByIdWithLeftpad(cep);

            if (cepOptional.isEmpty()) {
                cepEntity = findCepRetry(cep);
            } else {
                log.info("STEP -> CEP exists! Return values");

                cepEntity = cepOptional.get();
            }

            return transformCepEntityIntoCepDTO(cepEntity);
        } catch (InvalidCepException ex) {
            log.error(ex.getMessage());

            throw ex;
        }
    }

    @NewSpan
    private Cep findCepRetry(String cep) {
        log.info("STEP -> CEP not exists! Begin retry");

        int attempts = cep.length() - 1;
        String newCep;
        Optional<Cep> cepOptional = Optional.empty();

        while (attempts >= 0) {
            log.info("STEP -> Execute zero rightpad validation. Remaining attempts: {} ", attempts);

            newCep = StringUtils.rightPad(cep.substring(0, attempts), 8, "0");
            cepOptional = cepRepository.findByIdWithLeftpad(newCep);

            if (cepOptional.isPresent()) {
                break;
            } else {
                attempts--;
            }
        }

        log.info("STEP -> End of retry");

        if (cepOptional.isPresent()) {
            return cepOptional.get();
        } else {
            throw new InvalidCepException(cep);
        }
    }

    private CepDTO transformCepEntityIntoCepDTO(Cep cepEntity) {
        return CepDTO.builder()
                .bairro(cepEntity.getBairro())
                .cidade(cepEntity.getCidade())
                .estado(StateDetail.valueOf(cepEntity.getUf()).getName())
                .rua(cepEntity.getLogradouro())
                .build();
    }
}
