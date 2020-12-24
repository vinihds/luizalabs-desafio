package br.com.luizalabs.desafio.cep.core.exception;

public class InvalidCepException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Não existe dado para o cep informado: %s";

    public InvalidCepException(String cep) {
        super(String.format(DEFAULT_MESSAGE, cep));
    }

}
