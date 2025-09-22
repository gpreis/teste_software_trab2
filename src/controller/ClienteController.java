package controller;

import java.util.List;

import exception.ClienteInexistenteException;
import exception.ClienteJaCadastradoException;
import exception.IdadeNaoPermitidaException;
import service.ClienteService;

public class ClienteController {
	
    private ClienteService service;
    

    public ClienteController(ClienteService service) {
       this.service = service;
    }

    public void criarCliente(List<String> lista, boolean ativo) throws IdadeNaoPermitidaException, ClienteJaCadastradoException{
    	this.service.registrarCliente(lista, ativo);
    }
    
    public String buscarCliente(int id) throws ClienteInexistenteException{
    	return this.service.buscarCliente(id);
    }
    
    public String buscarClientePorConta(int id) throws ClienteInexistenteException{
    	return this.service.buscarClientePorConta(id);
    }
    
    public String listarClientes() throws ClienteInexistenteException{
    	return this.service.listarClientes();
    }
    
    public int buscarContaDoCliente(int id) throws ClienteInexistenteException {
    	return this.service.buscarContaDoCliente(id);
    }
    
    public void removerCliente(int id) throws ClienteInexistenteException {
    	this.service.removerCliente(id);
    }
}
