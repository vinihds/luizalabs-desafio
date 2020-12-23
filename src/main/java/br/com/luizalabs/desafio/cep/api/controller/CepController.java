package br.com.luizalabs.desafio.cep.api.controller;

import br.com.luizalabs.desafio.cep.api.constant.ApiConstants;
import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Log4j2
@Validated
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping(value = ApiConstants.API_BASE_V1_URL)
@RestController
public class CepController {

    private final CepService cepService;

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ApiConstants.Cep.CEP_PARAM_URN,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CepDTO findCep(@PathVariable(value = ApiConstants.Cep.CEP_PARAM)
                   @Size(min = 8, max = 8, message = "CEP inválido - CEP deve conter 8 digitos")
                   @Pattern(regexp = "[0-9]+", message = "CEP inválido - CEP deve conter somente números")
                           String cep) {
        log.info("STEP -> Received CEP value: {}", cep);

        return cepService.findCep(cep);
    }
}
