package br.com.luizalabs.desafio.cep.api.controller;

import br.com.luizalabs.desafio.cep.api.constant.ApiConstants;
import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.service.CepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ApiConstants.Cep.CEP_PARAM_URN,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    CepDTO findCep(@PathVariable(value = ApiConstants.Cep.CEP_PARAM)
                   @Size(min = 8, max = 8, message = "CEP must have 8 digits")
                   @Pattern(regexp = "[0-9]+", message = "CEP must have only numbers")
                           String cep) {
        log.info("CONTROLLER -> Received CEP value: {}", cep);

        return cepService.findCep(cep);
    }
}
