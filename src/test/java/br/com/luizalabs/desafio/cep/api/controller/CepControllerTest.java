package br.com.luizalabs.desafio.cep.api.controller;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.service.impl.CepServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.luizalabs.desafio.cep.core.mockfactory.MockFactory.mockCepDTO;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CepControllerTest {

    @Mock
    private CepServiceImpl cepService;

    @InjectMocks
    private CepController cepController;

    private final String cep_success = "12345678";

    @Before
    public void init() {
        when(cepService.findCep(cep_success))
                .thenReturn(mockCepDTO());
    }

    /**
     * Teste de sucesso padrão
     * <p>
     * É esperado que retorne o objeto de cep mockado de acordo com o valor informado
     */
    @Test
    public void whenFindCep_ThenReturnCep() {
        CepDTO expected = cepController.findCep(cep_success);

        assertNotNull(expected);
        verify(cepService, times(1)).findCep(cep_success);
    }

    /**
     * Teste de falha padrão
     * <p>
     * É esperado que não retorne nada devido a não existência de mock para o valor informado
     */
    @Test
    public void whenFindCep_ThenReturnNull() {
        CepDTO expected = cepController.findCep("");

        assertNull(expected);
        verify(cepService, times(1)).findCep("");
    }
}
