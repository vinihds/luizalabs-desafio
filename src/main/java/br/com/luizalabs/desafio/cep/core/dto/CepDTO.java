package br.com.luizalabs.desafio.cep.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO implements Serializable {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
}
