package br.com.luizalabs.desafio.cep.core.mockfactory;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.core.entity.Cep;
import br.com.luizalabs.desafio.cep.core.enums.StateDetail;

import java.util.Optional;

public class MockFactory {
    private MockFactory() {}

    public static CepDTO mockCepDTO() {
        return CepDTO.builder()
                .rua("rua")
                .bairro("bairro")
                .cidade("cidade")
                .estado("estado")
                .build();
    }

    public static Optional<Cep> mockOptionalCep(String cep) {
        return Optional.of(Cep.builder()
                .uf(StateDetail.SP.getUf())
                .logradouro("logradouro")
                .cidade("cidade")
                .cep(cep)
                .bairro("bairro")
                .build());
    }
}
