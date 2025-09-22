package dao;

import java.util.List;

import model.Cliente;

public interface ClienteRepository {
	
	public boolean inserir(Cliente cliente);
	public Cliente buscarPorId(int id);
	public Cliente buscarPorIdConta(int id);
	public boolean remover(int id);
	public List<Cliente> listar();
}
