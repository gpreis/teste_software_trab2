package service;

import exception.ContaInexistenteException;
import exception.ContaJaCadastradaException;
import exception.SaldoInsuficienteException;

public interface ContaCorrenteService {
	
	public String buscarContaCorrente(int id) throws ContaInexistenteException;
	
	public void registrarContaCorrente(int id, double saldo, boolean ativa) throws ContaJaCadastradaException, SaldoInsuficienteException;
	
	public void removerContaCorrente(int id) throws ContaInexistenteException;
	
	public void trasferir(int id_origem, int id_destino, double valor) throws ContaInexistenteException, SaldoInsuficienteException;

	public String listarContas() throws ContaInexistenteException;
}
