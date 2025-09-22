package controller;

import exception.ContaInexistenteException;
import exception.ContaJaCadastradaException;
import exception.SaldoInsuficienteException;
import service.ContaCorrenteService;


public class ContaCorrenteController {

	private ContaCorrenteService service;
	
	public ContaCorrenteController() {
		
	}
	
	public ContaCorrenteController(ContaCorrenteService service) {
		this.service = service;
	}
	
	public void criarContaCorrente(int id, double saldo, boolean ativo) throws ContaJaCadastradaException, SaldoInsuficienteException{
		this.service.registrarContaCorrente(id, saldo, ativo);
	}
	
	public String buscarContaCorrente(int id) throws ContaInexistenteException{
		return this.service.buscarContaCorrente(id).toString();
	}
	
	public String listarContas() throws ContaInexistenteException{
		return this.service.listarContas();
	}
	
	public void removerConta(int id) throws ContaInexistenteException {
		this.service.removerContaCorrente(id);
	}
	
	public void transferir(int id_origem, int id_destino, double valor) throws ContaInexistenteException, SaldoInsuficienteException {
		this.service.trasferir(id_origem, id_destino, valor);
	}
}
