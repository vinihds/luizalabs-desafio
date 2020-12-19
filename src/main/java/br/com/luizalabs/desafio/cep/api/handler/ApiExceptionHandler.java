package br.com.luizalabs.desafio.cep.api.handler;

import br.com.luizalabs.desafio.cep.core.dto.ResponseErrorDTO;
import br.com.luizalabs.desafio.cep.core.exception.InvalidCepException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = InvalidCepException.class)
    protected ResponseEntity<Object> handleInvalidCepException(InvalidCepException ex) {
        log.error(ex);

        List<ResponseErrorDTO> errorList = List.of(ResponseErrorDTO.builder()
                .status(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .build());

        return new ResponseEntity<>(errorList, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(ex);

        List<ResponseErrorDTO> errorList = ex.getConstraintViolations().stream()
                .map(violation -> ResponseErrorDTO.builder()
                        .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toList());

        return new ResponseEntity<>(errorList, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        log.error(ex);

        List<ResponseErrorDTO> errorList = List.of(ResponseErrorDTO.builder()
                .status(ex.getStatusCode().toString())
                .message(ex.getResponseBodyAsString())
                .build());

        return new ResponseEntity<>(errorList, ex.getStatusCode());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    protected ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex) {
        log.error(ex);

        List<ResponseErrorDTO> errorList = List.of(ResponseErrorDTO.builder()
                .status(ex.getStatusCode().toString())
                .message(ex.getResponseBodyAsString())
                .build());

        return new ResponseEntity<>(errorList, ex.getStatusCode());
    }

}
