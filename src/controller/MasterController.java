package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exception.ClienteInexistenteException;
import exception.ClienteJaCadastradoException;
import exception.ContaInexistenteException;
import exception.ContaJaCadastradaException;
import exception.IdadeNaoPermitidaException;
import exception.SaldoInsuficienteException;
import view.interfaces.MainFrameInterface;
import view.interfaces.TelaCadastroInterface;
import view.interfaces.TelaConsultaInterface;
import view.interfaces.TelaMenuInterface;
import view.interfaces.TelaTransferenciaInterface;

public class MasterController {

	private ClienteController cliente;
	private ContaCorrenteController conta;
	
	private MainFrameInterface mainFrame;
	
	private TelaMenuInterface telaMenu;
	
	private TelaCadastroInterface telaCadastro;
	
	private TelaConsultaInterface telaConta;
	private TelaConsultaInterface telaCliente;
	private TelaConsultaInterface telaExcluir;
	
	private TelaTransferenciaInterface telaTransferencia;
	
	public MasterController(ClienteController cliente, ContaCorrenteController conta, 
			MainFrameInterface mainFrame, TelaMenuInterface telaMenu, TelaCadastroInterface telaCadastro,
			TelaConsultaInterface telaConta, TelaConsultaInterface telaCliente, TelaConsultaInterface telaExcluir,
			TelaTransferenciaInterface telaTransferencia ) {
		
		this.cliente = cliente;
		this.conta = conta;
		
		this.mainFrame = mainFrame;
		
		this.telaMenu = telaMenu;
		
		this.telaCadastro = telaCadastro;
		
		this.telaConta = telaConta;
		this.telaCliente = telaCliente;
		this.telaExcluir = telaExcluir;
		
		this.telaTransferencia = telaTransferencia;
		
		this.carregarTelas();
		this.iniciarBtns();
		
		this.mainFrame.showTela("menu");
	}
	
	private void iniciarBtns() {
		
		telaMenu.setListenerBtnAbrirConta(e -> {this.abrirConta();});
		telaMenu.setListenerBtnConsultarConta(e -> {this.consutlarConta();});	
		telaMenu.setListenerBtnConsultarCliente(e -> {this.consultarCliente();});
		telaMenu.setListenerBtnExcluir(e -> {this.excluir();});
		telaMenu.setListenerBtnAbrirTransferencia(e -> {this.abrirTransferencia();});
		
		telaCadastro.setListenerBtnConfirmar(e -> {this.registrarCliente();});
		telaCadastro.setListenerBtnVoltar(e -> {this.inicio();});
		
		telaConta.setListenerBtnConfirmar(e -> {this.buscarConta();});
		telaConta.setListenerBtnVoltar(e -> {this.inicio();});
		
		telaCliente.setListenerBtnConfirmar(e -> {this.buscarCliente();});
		telaCliente.setListenerBtnVoltar(e ->{this.inicio();});
		
		telaExcluir.setListenerBtnConfirmar(e -> {this.remover();});
		telaExcluir.setListenerBtnVoltar(e -> {this.inicio();});
		
		telaTransferencia.setListenerBtnConfirmar(e -> {this.transferir();});
		telaTransferencia.setListenerBtnVoltar(e -> {this.inicio();});
	}
	
	private void carregarTelas() {
		this.mainFrame.addPanel(this.telaMenu.getPanel(), "menu");
		this.mainFrame.addPanel(this.telaCadastro.getPanel(), "cadastro");
		this.mainFrame.addPanel(this.telaConta.getPanel(), "conta");
		this.mainFrame.addPanel(this.telaCliente.getPanel(), "cliente");
		this.mainFrame.addPanel(this.telaExcluir.getPanel(), "excluir");
		this.mainFrame.addPanel(this.telaTransferencia.getPanel(), "transferir");
	}
	
	public void inicio() {
		this.telaCadastro.limparCampos();
		this.telaConta.limparCampos();
		this.telaCliente.limparCampos();
		this.telaExcluir.limparCampos();
		this.telaTransferencia.limparCampos();
		
		this.mainFrame.showTela("menu");
	}
	
	public void abrirConta() {
		this.mainFrame.showTela("cadastro");
	}
	
	public void consutlarConta() {
		this.mainFrame.showTela("conta");
	}
	
	public void consultarCliente() {
		this.mainFrame.showTela("cliente");
	}
	
	public void excluir() {
		this.mainFrame.showTela("excluir");
		
	}
	
	public void abrirTransferencia() {
		this.mainFrame.showTela("transferir");
	}
	
	public void registrarCliente() {
		List<String> lista = new ArrayList<>();
		lista.add(this.telaCadastro.getIdCliente());
		lista.add(this.telaCadastro.getNomeCliente());
		lista.add(this.telaCadastro.getIdadeCliente());
		lista.add(this.telaCadastro.getEmail());
		lista.add(this.telaCadastro.getIdConta());
		
		int id = Integer.parseInt(this.telaCadastro.getIdConta());
		try {
		    this.conta.criarContaCorrente(
		        id,
		        Double.parseDouble(this.telaCadastro.getSaldo()), 
		        this.telaCadastro.getAtivoConta()
		    );

		    this.cliente.criarCliente(lista, this.telaCadastro.getAtivoCliente());
			this.inicio();
		} 
		catch (ContaJaCadastradaException | SaldoInsuficienteException | ClienteJaCadastradoException | IdadeNaoPermitidaException e) {
				try {
					this.conta.removerConta(id);
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} 
				catch (ContaInexistenteException e1) {
					e1.printStackTrace();
				}
		}
		
	}
	
	public void buscarConta() {
		if(this.telaConta.getId() == null || this.telaConta.getId().isBlank()) {
			try {
				this.telaConta.setAreaDetalhes(this.conta.listarContas());
			} catch (ContaInexistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			int id = Integer.parseInt(this.telaConta.getId()) ;
			try {
				this.telaConta.setAreaDetalhes(this.conta.buscarContaCorrente(id)+this.cliente.buscarClientePorConta(id)+"\n");
			} 
			catch (ContaInexistenteException | ClienteInexistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void buscarCliente() {
		if(this.telaCliente.getId() == null || this.telaCliente.getId().isBlank()) {
			try {
				this.telaCliente.setAreaDetalhes(this.cliente.listarClientes());
			} catch (ClienteInexistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			int id = Integer.parseInt(this.telaCliente.getId());
			try {
				this.telaCliente.setAreaDetalhes(this.cliente.buscarCliente(id));
			} 
			catch (ClienteInexistenteException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void remover() {
		int id = Integer.parseInt(this.telaExcluir.getId());
		try {
			int id_conta = this.cliente.buscarContaDoCliente(id);
			this.cliente.removerCliente(id);
			this.conta.removerConta(id_conta);
			
			this.telaExcluir.setAreaDetalhes("Cliente e Conta Removidos com Sucesso!!");
		}
		catch(ClienteInexistenteException | ContaInexistenteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			this.telaExcluir.limparCampos();
		}

	}
	
	public void transferir() {
		int id_origem = Integer.parseInt(this.telaTransferencia.getIdContaOrigem());
		int id_destino = Integer.parseInt(this.telaTransferencia.getIdContaDestino());
		double valor = Double.parseDouble(this.telaTransferencia.getValor());
		
		try {
			this.conta.transferir(id_origem, id_destino, valor);
			this.telaTransferencia.setAreaDetalhes(String.format("Transferencia realizada com sucesso!! \nValor R$ %.2f transferido da conta: %d para a conta: %d",valor, id_origem, id_destino));
		}
		catch(ContaInexistenteException | SaldoInsuficienteException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
