package teste;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import model.Cliente;
import dao.ClienteRepository;
import exception.ClienteInexistenteException;
import exception.ClienteJaCadastradoException;
import exception.IdadeNaoPermitidaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClienteServiceImpl;

public class ClienteServiceImplTeste {
	private ClienteRepository repository;
	private ClienteServiceImpl service;

	@BeforeEach
	void setUp() {
		repository = mock(ClienteRepository.class);
		service = new ClienteServiceImpl(repository);
	}

	@Test
	void testBuscarCliente_Success() throws ClienteInexistenteException {
		Cliente cliente = new Cliente(1, "Nome", 30, "email@test.com", 2, true);
		when(repository.buscarPorId(1)).thenReturn(cliente);
		String result = service.buscarCliente(1);
		assertTrue(result.contains("Nome"));
	}

	@Test
	void testBuscarCliente_NotFound() {
		when(repository.buscarPorId(1)).thenReturn(null);
		assertThrows(ClienteInexistenteException.class, () -> service.buscarCliente(1));
	}

	@Test
	void testBuscarClientePorConta_Success() throws ClienteInexistenteException {
		Cliente cliente = new Cliente(1, "Nome", 30, "email@test.com", 2, true);
		when(repository.buscarPorIdConta(2)).thenReturn(cliente);
		String result = service.buscarClientePorConta(2);
		assertTrue(result.contains("Nome"));
	}

	@Test
	void testBuscarClienregistrarClientetePorConta_NotFound() {
		when(repository.buscarPorIdConta(2)).thenReturn(null);
		assertThrows(ClienteInexistenteException.class, () -> service.buscarClientePorConta(2));
	}

	@Test
	void testRegistrarCliente_Success() throws Exception {
		List<String> lista = Arrays.asList("1", "Nome", "30", "email@test.com", "2");
		when(repository.buscarPorId(1)).thenReturn(null);
		service.registrarCliente(lista, true);
		verify(repository, times(1)).inserir(any(Cliente.class));
	}

	@Test
	void testRegistrarCliente_AlreadyExists() {
		List<String> lista = Arrays.asList("1", "Nome", "30", "email@test.com");
		when(repository.buscarPorId(1)).thenReturn(new Cliente(1, "Nome", 30, "email@test.com", 2, true));
		assertThrows(ClienteJaCadastradoException.class, () -> service.registrarCliente(lista, true));
	}

	@Test
	void testRegistrarCliente_IdadeNaoPermitida() {
		List<String> lista = Arrays.asList("1", "Nome", "17", "email@test.com", "2");
		when(repository.buscarPorId(1)).thenReturn(null);
		assertThrows(IdadeNaoPermitidaException.class, () -> service.registrarCliente(lista, true));
	}
}