package br.com.luizalabs.desafio.cep.service;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.sleuth.annotation.NewSpan;

public interface CepService {

    @NewSpan
    @Cacheable(value = "persist-cep", key = "#cep")
    CepDTO findCep(String cep);

}
