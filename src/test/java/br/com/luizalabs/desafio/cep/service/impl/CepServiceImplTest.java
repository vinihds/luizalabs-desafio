package br.com.luizalabs.desafio.cep.service.impl;

import br.com.luizalabs.desafio.cep.core.dto.CepDTO;
import br.com.luizalabs.desafio.cep.core.exception.InvalidCepException;
import br.com.luizalabs.desafio.cep.core.repository.CepRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static br.com.luizalabs.desafio.cep.core.mockfactory.MockFactory.mockOptionalCep;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class CepServiceImplTest {

    @Mock
    private CepRepository cepRepository;

    @InjectMocks
    private CepServiceImpl cepService;

    private final String cep_success = "12345678";
    private final String cep_one_rightpad = "12345670";
    private final String cep_two_rightpad = "12345600";
    private final String cep_three_rightpad = "12345000";
    private final String cep_four_rightpad = "12340000";
    private final String cep_five_rightpad = "12300000";
    private final String cep_six_rightpad = "12000000";
    private final String cep_seven_rightpad = "10000000";
    private final String cep_eight_rightpad = "00000000";

    /**
     * Implementando mock padrão de todos os possiveis cenários, envolvendo sucesso e todas as possiveis
     * retentativas com a substituiçao por zero a direita
     */
    @Before
    public void init() {
        when(cepRepository.findByIdWithLeftpad(cep_success))
                .thenReturn(mockOptionalCep(cep_success));

        when(cepRepository.findByIdWithLeftpad(cep_one_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_two_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_three_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_four_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_five_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_six_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_seven_rightpad))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_eight_rightpad))
                .thenReturn(Optional.empty());
    }

    /**
     * Teste de sucesso padrão
     * <p>
     * É esperado que retorne o objeto de cep mockado conforme valor informado.
     * Além disso, é esperado que passe apenas uma vez pelo repository
     */
    @Test
    public void whenFindCepAndValidCep_ThenReturnCepDTO() {
        CepDTO expected = cepService.findCep(cep_success);

        assertNotNull(expected);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_success);
    }

    /**
     * Teste de sucesso com algumas retentativas
     * <p>
     * É esperado que retorne o objeto de cep mockado após executar o processo de retentativas 3 vezes
     * Com isso, é esperado que o repository seja chamado apenas uma vez por cada valor de cep
     * No total, é esperado que o repository seja chamado 4 vezes
     */
    @Test
    public void whenFindCepAndValidCepAndZeroRightpad_ThenReturnCepDTO() {
        when(cepRepository.findByIdWithLeftpad(cep_success))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_three_rightpad))
                .thenReturn(mockOptionalCep(cep_three_rightpad));

        CepDTO expected = cepService.findCep(cep_success);

        assertNotNull(expected);

        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_success);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_one_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_two_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_three_rightpad);

        verify(cepRepository, times(4)).findByIdWithLeftpad(anyString());
    }

    /**
     * Teste de sucesso com todas as retentativas possiveis
     * <p>
     * É esperado que retorne o objeto de cep mockado após executar o processo de retentativas 8 vezes
     * Com isso, é esperado que o repository seja chamado apenas uma vez por cada valor de cep
     * No total, é esperado que o repository seja chamado 9 vezes
     */
    @Test
    public void whenFindCepAndValidCepAndZeroRightpadAndAllAttempts_ThenReturnCepDTO() {
        when(cepRepository.findByIdWithLeftpad(cep_success))
                .thenReturn(Optional.empty());

        when(cepRepository.findByIdWithLeftpad(cep_eight_rightpad))
                .thenReturn(mockOptionalCep(cep_eight_rightpad));

        CepDTO expected = cepService.findCep(cep_success);

        assertNotNull(expected);

        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_success);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_one_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_two_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_three_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_four_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_five_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_six_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_seven_rightpad);
        verify(cepRepository, times(1)).findByIdWithLeftpad(cep_eight_rightpad);

        verify(cepRepository, times(9)).findByIdWithLeftpad(anyString());
    }

    /**
     * Teste de exceção padrão
     * <p>
     * É esperado que não encontre o cep informado e dispare a exceção
     */
    @Test(expected = InvalidCepException.class)
    public void whenFindCepAndValidCepAndZeroRightpad_ThenThrowInvalidCepException() {
        when(cepRepository.findByIdWithLeftpad(cep_success))
                .thenReturn(Optional.empty());

        cepService.findCep(cep_success);
    }

}
