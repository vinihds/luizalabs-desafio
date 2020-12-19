package br.com.luizalabs.desafio.cep.service.impl;

import br.com.luizalabs.desafio.cep.core.client.ViaCepClient;
import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.core.dto.ViaCepDTO;
import br.com.luizalabs.desafio.cep.core.enums.StateDetail;
import br.com.luizalabs.desafio.cep.core.exception.InvalidCepException;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class CepServiceImpl implements CepService {

    private final ViaCepClient viaCepClient;

    @Override
    public CepDTO findCep(String cep) {
        log.info("SERVICE -> Received CEP value: {}", cep);

        try {
            ViaCepDTO viaCepDTO = viaCepClient.findViaCepByCepValue(cep);

            if (viaCepDTO.isErro()) {
                viaCepDTO = findCepRetry(cep);
            }

            return transformViaCepIntoCep(viaCepDTO);
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            log.error(ex.getResponseBodyAsString());

            throw ex;
        } catch (InvalidCepException ex) {
            log.error(ex.getMessage());

            throw ex;
        }
    }

    private ViaCepDTO findCepRetry(String cep) throws InvalidCepException {
        char[] charArray = cep.toCharArray();

        int attempts = cep.length() - 1;
        String newCep;
        ViaCepDTO viaCepDTO = null;

        while (attempts > 0) {
            for (int i = charArray.length - 1; i >= attempts; i--) {
                charArray[i] = "0".charAt(0);
            }

            newCep = new String(charArray);
            viaCepDTO = viaCepClient.findViaCepByCepValue(newCep);

            if (viaCepDTO != null && !viaCepDTO.isErro()) {
                break;
            } else {
                attempts--;
            }
        }

        if (viaCepDTO != null && !viaCepDTO.isErro()) {
            return viaCepDTO;
        } else {
            throw new InvalidCepException(cep);
        }
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
