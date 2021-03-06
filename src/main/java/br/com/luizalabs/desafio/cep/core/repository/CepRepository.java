package br.com.luizalabs.desafio.cep.core.repository;

import br.com.luizalabs.desafio.cep.core.entity.Cep;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CepRepository extends JpaRepository<Cep, String> {

    @NewSpan
    Optional<Cep> findByIdWithLeftpad(String cep);
}
