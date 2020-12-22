package br.com.luizalabs.desafio.cep.service;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import org.springframework.cloud.sleuth.annotation.NewSpan;

public interface CepService {

    @NewSpan
    CepDTO findCep(String cep);

}
