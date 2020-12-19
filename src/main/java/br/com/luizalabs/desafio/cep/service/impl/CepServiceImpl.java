package br.com.luizalabs.desafio.cep.service.impl;

import br.com.luizalabs.desafio.cep.core.enums.StateDetail;
import br.com.luizalabs.desafio.cep.core.client.ViaCepClient;
import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.core.dto.ViaCepDTO;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class CepServiceImpl implements CepService {

    private final ViaCepClient viaCepClient;

    @Override
    public CepDTO findCep(String cep) {
        log.info("SERVICE -> Received CEP value: {}", cep);

        return transformViaCepIntoCep(viaCepClient.findViaCepByCepValue(cep));
    }

    private CepDTO transformViaCepIntoCep(ViaCepDTO viaCepDTO) {
        return CepDTO.builder()
                .bairro(viaCepDTO.getBairro())
                .cidade(viaCepDTO.getLocalidade())
                .estado(StateDetail.valueOf(viaCepDTO.getUf()).getName())
                .rua(viaCepDTO.getLogradouro())
                .build();
    }
}
