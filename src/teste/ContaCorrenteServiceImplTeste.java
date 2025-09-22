package teste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dao.ContaCorrenteRepository;
import exception.ContaInexistenteException;
import exception.ContaJaCadastradaException;
import exception.SaldoInsuficienteException;
import model.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ContaCorrenteServiceImpl;
import java.util.*;

public class ContaCorrenteServiceImplTeste {
    private ContaCorrenteRepository repository;
    private ContaCorrenteServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(ContaCorrenteRepository.class);
        service = new ContaCorrenteServiceImpl(repository);
    }

    @Test
    void testBuscarContaCorrente_Success() throws ContaInexistenteException {
        ContaCorrente conta = new ContaCorrente(1, 100.0, true);
        when(repository.buscarPorId(1)).thenReturn(conta);
        String result = service.buscarContaCorrente(1);
        assertTrue(result.contains("100.0"));
    }

    @Test
    void testBuscarContaCorrente_NotFound() {
        when(repository.buscarPorId(1)).thenReturn(null);
        assertThrows(ContaInexistenteException.class, () -> service.buscarContaCorrente(1));
    }

    @Test
    void testRegistrarContaCorrente_Success() throws Exception {
        when(repository.buscarPorId(1)).thenReturn(null);
        service.registrarContaCorrente(1, 100.0, true);
        verify(repository, times(1)).inserir(any(ContaCorrente.class));
    }

    @Test
    void testRegistrarContaCorrente_AlreadyExists() {
        when(repository.buscarPorId(1)).thenReturn(new ContaCorrente(1, 100.0, true));
        assertThrows(ContaJaCadastradaException.class, () -> service.registrarContaCorrente(1, 100.0, true));
    }

    @Test
    void testRegistrarContaCorrente_SaldoInsuficiente() {
        when(repository.buscarPorId(1)).thenReturn(null);
        assertThrows(SaldoInsuficienteException.class, () -> service.registrarContaCorrente(1, -10.0, true));
    }

    @Test
    void testRemoverContaCorrente_Success() throws ContaInexistenteException {
        when(repository.remover(1)).thenReturn(true);
        service.removerContaCorrente(1);
        verify(repository, times(1)).remover(1);
    }

    @Test
    void testRemoverContaCorrente_NotFound() {
        when(repository.remover(1)).thenReturn(false);
        assertThrows(ContaInexistenteException.class, () -> service.removerContaCorrente(1));
    }
}
