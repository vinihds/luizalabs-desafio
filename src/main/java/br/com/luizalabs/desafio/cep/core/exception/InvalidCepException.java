package br.com.luizalabs.desafio.cep.core.exception;

public class InvalidCepException extends RuntimeException {

    private final static String DEFAULT_MESSAGE = "There is no data for the entered zip code: %s";

    public InvalidCepException(String cep) {
        super(String.format(DEFAULT_MESSAGE, cep));
    }

    public InvalidCepException(String cep, Throwable throwable) {
        super(String.format(DEFAULT_MESSAGE, cep), throwable);
    }
}
