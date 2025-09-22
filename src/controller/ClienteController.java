package controller;

import java.util.List;

import dao.ClienteDao;
import exception.IdadeNaoPermitidaException;
import model.Cliente;

public class ClienteController {
	
    private ClienteDao clienteDao;
    
    public ClienteController() {
    	
    }

    public ClienteController(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Cliente> getClientesDoBanco() {
        return clienteDao.listar();
    }

    public Cliente pesquisaCliente(int idCliente) {
        return clienteDao.buscarPorId(idCliente);
    }

    public void adicionaCliente(Cliente novoCliente) throws IdadeNaoPermitidaException {
        validaIdade(novoCliente.getIdade());
        clienteDao.inserir(novoCliente);
    }

    public boolean removeCliente(int idCliente) {
        return clienteDao.remover(idCliente);
    }

    public boolean clienteAtivo(int idCliente) {
        Cliente cliente = clienteDao.buscarPorId(idCliente);
        return cliente != null && cliente.isAtivo();
    }

    public boolean validaIdade(int idade) throws IdadeNaoPermitidaException {
        if (idade < 18 || idade > 65) {
            throw new IdadeNaoPermitidaException(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA);
        }
        return true;
    }
}
