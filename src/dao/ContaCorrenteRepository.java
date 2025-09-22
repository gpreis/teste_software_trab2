package dao;

import java.util.List;

import model.ContaCorrente;

public interface ContaCorrenteRepository {
	
	public boolean inserir(ContaCorrente conta);
	public ContaCorrente buscarPorId(int id);
	public boolean contaAtiva(int id);
	public boolean remover(int id);
	public boolean transfereValor(int id_origem, int id_destino, double valor);
	public List<ContaCorrente> getContasDoBanco();
}