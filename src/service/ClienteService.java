package service;

import java.util.List;

import exception.ClienteInexistenteException;
import exception.ClienteJaCadastradoException;
import exception.IdadeNaoPermitidaException;

public interface ClienteService {
	
	public String buscarCliente(int id) throws ClienteInexistenteException;
	
	public String buscarClientePorConta(int id) throws ClienteInexistenteException;
	
	public int buscarContaDoCliente(int id) throws ClienteInexistenteException;
	
	public void registrarCliente(List<String> lista, boolean ativo) throws IdadeNaoPermitidaException, ClienteJaCadastradoException;
	
	public void removerCliente(int id) throws ClienteInexistenteException;
	
	public String listarClientes() throws ClienteInexistenteException;
}