package br.com.luizalabs.desafio.cep.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ceps")
@NamedQuery(name = "Cep.findByIdWithLeftpad", query = "SELECT c FROM Cep AS c WHERE LPAD(c.cep, 8, '0') = ?1")
public class Cep {

    @Id
    private String cep;

    private String cidade;
    private String uf;
    private String bairro;
    private String logradouro;
}
