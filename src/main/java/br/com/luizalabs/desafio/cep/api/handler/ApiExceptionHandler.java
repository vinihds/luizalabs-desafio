package br.com.luizalabs.desafio.cep.api.handler;

import br.com.luizalabs.desafio.cep.core.dto.ResponseErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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
}
