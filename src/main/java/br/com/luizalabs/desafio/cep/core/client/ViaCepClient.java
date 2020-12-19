package br.com.luizalabs.desafio.cep.core.client;

import br.com.luizalabs.desafio.cep.api.constant.ApiConstants;
import br.com.luizalabs.desafio.cep.core.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viaCepClient", url = ApiConstants.ViaCep.VIA_CEP_BASE_URL)
public interface ViaCepClient {

    @GetMapping(value = ApiConstants.ViaCep.CEP_PARAM_URN, produces = MediaType.APPLICATION_JSON_VALUE)
    ViaCepDTO findViaCepByCepValue(@PathVariable(value = ApiConstants.ViaCep.CEP_PARAM) String cep);
}
