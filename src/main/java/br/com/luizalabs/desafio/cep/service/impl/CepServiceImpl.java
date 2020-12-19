package br.com.luizalabs.desafio.cep.service.impl;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Service
public class CepServiceImpl implements CepService {

    @Override
    public CepDTO findCep(String cep) {
        log.info("SERVICE -> Received CEP value: {}", cep);

        return CepDTO.builder()
                .bairro("Test")
                .cidade("Test")
                .estado("Test")
                .rua("test")
                .build();
    }
}
