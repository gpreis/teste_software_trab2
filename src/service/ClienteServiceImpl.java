package service;

import java.util.List;
import java.util.stream.Collectors;

import model.Cliente;

import dao.ClienteRepository;
import exception.ClienteInexistenteException;
import exception.ClienteJaCadastradoException;
import exception.IdadeNaoPermitidaException;

public class ClienteServiceImpl implements ClienteService{
	
	private ClienteRepository repository;
	
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public String buscarCliente(int id) throws ClienteInexistenteException{
		if(this.repository.buscarPorId(id) == null) {
			throw new ClienteInexistenteException("Cliente Inexistente!!");
		}
		else {
			return this.repository.buscarPorId(id).toString();
		}
	}
	
	public String buscarClientePorConta(int id) throws ClienteInexistenteException {
		if(this.repository.buscarPorIdConta(id) == null) {
			throw new ClienteInexistenteException("Cliente Inexistente!!");
		}
		else {
			return this.repository.buscarPorIdConta(id).toString();
		}
	}

	@Override
	public void registrarCliente(List<String> lista, boolean ativo) throws IdadeNaoPermitidaException, ClienteJaCadastradoException{
		
		int id = Integer.parseInt(lista.get(0));
		if(this.repository.buscarPorId(id) == null) {
			String nome = lista.get(1);
			int idade = Integer.parseInt(lista.get(2));
			if(idade < 18 || idade > 65) {
				throw new IdadeNaoPermitidaException("Idade Invalida");
			}
			String email = lista.get(3);
			int idContaCorrente = Integer.parseInt(lista.get(4)); 
			
			Cliente cliente = new Cliente(id, nome, idade, email, idContaCorrente, ativo);
					
			this.repository.inserir(cliente);
		}else {
			throw new ClienteJaCadastradoException("Cliente ja cadastrado!");
		}
		
		
	}

	@Override
	public void removerCliente(int id) throws ClienteInexistenteException{
		if(!(this.repository.remover(id))) {
			throw new ClienteInexistenteException("Cliente Inexistente!!");
		}
	}

	@Override
	public int buscarContaDoCliente(int id) throws ClienteInexistenteException {
		
		Cliente cliente = this.repository.buscarPorId(id);
		
		if(cliente == null) {
			throw new ClienteInexistenteException("Cliente Inexistente!!");
		}
		else {
			return cliente.getIdContaCorrente();
		}
	}

	@Override
	public String listarClientes() throws ClienteInexistenteException {
		String lista = null;
		List<Cliente> clientes = this.repository.listar();
		
		if (clientes != null && !clientes.isEmpty()) {
		    lista = clientes.stream()
		                    .map(c -> c == null ? "null" : c.toString())
		                    .collect(Collectors.joining("\n"));
		}
		if(lista == null) {
			throw new ClienteInexistenteException("Nenhum Cliente Cadastrado!!");
		}
		else {
			return lista;
		}
	}
}
