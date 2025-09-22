package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.interfaces.TelaMenuInterface;


@SuppressWarnings("serial")
public class TelaMenu extends JPanel implements TelaMenuInterface{

	  	private JPanel painelInicial;
	  	
	  	private JButton btnAbrirConta;
	    private JButton btnConsultarConta;
	    private JButton btnConsultarCliente;
	    private JButton btnExcluir;
	    private JButton btnAbrirTransferencia;
	
	public TelaMenu() {
		
		painelInicial = new JPanel(new GridLayout(5, 1, 10, 10));
        btnAbrirConta = new JButton("Abrir Conta");
        btnConsultarConta = new JButton("Consultar Conta");
        btnConsultarCliente = new JButton("Consultar Cliente");
        btnAbrirTransferencia = new JButton("Transferir");
        btnExcluir = new JButton("Excluir");

        btnAbrirConta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnConsultarConta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnConsultarCliente.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnAbrirTransferencia.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnExcluir.setFont(new Font("SansSerif", Font.PLAIN, 18));

        painelInicial.add(btnAbrirConta);
        painelInicial.add(btnConsultarConta);
        painelInicial.add(btnConsultarCliente);
        painelInicial.add(btnAbrirTransferencia);
        painelInicial.add(btnExcluir);
        
        this.add(painelInicial);
	}
	
	public void setListenerBtnAbrirConta(ActionListener listener) {
		this.btnAbrirConta.addActionListener(listener);
	}
	
	public void setListenerBtnConsultarConta(ActionListener listener) {
		this.btnConsultarConta.addActionListener(listener);
	}
	
	public void setListenerBtnConsultarCliente(ActionListener listener) {
		this.btnConsultarCliente.addActionListener(listener);
	}
	
	public void setListenerBtnExcluir(ActionListener listener) {
		this.btnExcluir.addActionListener(listener);
	}
	
	public void setListenerBtnAbrirTransferencia(ActionListener listener) {
		this.btnAbrirTransferencia.addActionListener(listener);
	}
	
	public JPanel getPanel() {
		return this;
	}
}
