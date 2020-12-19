package br.com.luizalabs.desafio.cep.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CepDTO {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
}
