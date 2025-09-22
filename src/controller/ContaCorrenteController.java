package controller;

import java.util.List;
import dao.ContaDao;
import model.ContaCorrente;

public class ContaCorrenteController {

	private ContaDao contaDao;
	
	public ContaCorrenteController() {
		
	}
	
	public ContaCorrenteController(ContaDao contaDao) {
		this.contaDao = contaDao;
	}
	
	public List<ContaCorrente> getContasDoBanco() {
		return contaDao.getContasDoBanco();
	}

	public ContaCorrente pesquisaConta (int idConta) {
		return contaDao.pesquisaConta(idConta);
	}
	
	public void adicionaConta (ContaCorrente novaConta) {
		contaDao.adicionaConta(novaConta);
	}

	
	public boolean removeConta (int idConta) {
		return contaDao.removeConta(idConta);
	}
	
	public boolean contaAtiva (int idConta) {		
		return contaDao.contaAtiva(idConta);
	}	
	
	public boolean transfereValor (int idContaOrigem, double valor, int idContaDestino) {
		return contaDao.transfereValor(idContaOrigem, valor, idContaDestino);	
	}	
	
}
