package br.com.luizalabs.desafio.cep.service;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;

public interface CepService {

    CepDTO findCep(String cep);
}
