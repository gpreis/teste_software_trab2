package service;

import java.util.List;
import java.util.stream.Collectors;

import dao.ContaCorrenteRepository;
import exception.ContaInexistenteException;
import exception.ContaJaCadastradaException;
import exception.SaldoInsuficienteException;
import model.ContaCorrente;

public class ContaCorrenteServiceImpl implements ContaCorrenteService{

	private ContaCorrenteRepository repository;
	
	public ContaCorrenteServiceImpl(ContaCorrenteRepository repository) {
		this.repository = repository;
	}
	@Override
	public String buscarContaCorrente(int id) throws ContaInexistenteException{
		if(this.repository.buscarPorId(id) == null) {
			throw new ContaInexistenteException("Conta Inexistente!!");
		}
		else {
			return this.repository.buscarPorId(id).toString();
		}
			
	}

	@Override
	public void registrarContaCorrente(int id, double saldo, boolean ativa) throws ContaJaCadastradaException, SaldoInsuficienteException{
		
		if(this.repository.buscarPorId(id) == null) {
			if(saldo < 0) {
				throw new SaldoInsuficienteException("Saldo Insuficiente!!");
			}
			else {
				ContaCorrente conta = new ContaCorrente(id, saldo, ativa);
				this.repository.inserir(conta);
			}
		}
		else {
			throw new ContaJaCadastradaException("Conta Ja Cadastrada!!");
		}
		
	}

	@Override
	public void removerContaCorrente(int id) throws ContaInexistenteException{
		if(!(this.repository.remover(id))) {
			throw new ContaInexistenteException("Conta Inexistente!!");
		}
	}

	@Override
	public void trasferir(int id_origem, int id_destino, double valor) throws ContaInexistenteException, SaldoInsuficienteException{
		if((this.repository.contaAtiva(id_origem)) && (this.repository.contaAtiva(id_destino))) {
			if(!(this.repository.transfereValor(id_origem, id_destino, valor))) {
				throw new SaldoInsuficienteException("Saldo insuficiente!!");
			}
		}
		else {
			throw new ContaInexistenteException("Conta Indisponivel!!");
		}
		
	}
	@Override
	public String listarContas() throws ContaInexistenteException {
		String lista = null;
		List<ContaCorrente> contas = this.repository.getContasDoBanco();
		
		if (contas != null && !contas.isEmpty()) {
		    lista = contas.stream()
		                    .map(c -> c == null ? "null" : c.toString())
		                    .collect(Collectors.joining("\n"));
		}
		if(lista == null) {
			throw new ContaInexistenteException("Nenhuma conta cadastrada!!");
		}
		else {
			return lista;
		}
	}
}
