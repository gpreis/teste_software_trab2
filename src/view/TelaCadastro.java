package view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.interfaces.TelaCadastroInterface;

@SuppressWarnings("serial")
public class TelaCadastro extends JPanel implements TelaCadastroInterface{
    
	private JPanel painelAbrirConta;
    
    private JTextField campoIdRegistrarCliente;
    private JTextField campoNomeCliente;
    private JTextField campoIdadeCliente;
    private JTextField campoEmailCliente;
    
    private JCheckBox ativoClienteCheck;
    
    private JTextField campoIdContaRegistrar;
    private JTextField campoSaldo;
    
    private JCheckBox ativoContaCheck;
    
    private JButton btnConfirmar;
    private JButton btnVoltarAbrirConta;
    
    
	
	public TelaCadastro() {
		
		painelAbrirConta = new JPanel(new GridLayout(0, 2, 5, 5));
        campoSaldo = new JTextField();
        ativoContaCheck = new JCheckBox("Ativa", true);
        campoIdRegistrarCliente = new JTextField();
        campoNomeCliente = new JTextField();
        campoIdadeCliente = new JTextField();
        campoEmailCliente = new JTextField();
        campoIdContaRegistrar = new JTextField();
        ativoClienteCheck = new JCheckBox();
        
        this.btnConfirmar = new JButton("Criar Conta");
        this.btnVoltarAbrirConta = new JButton("Voltar");

        painelAbrirConta.add(new JLabel("ID Cliente"));
        painelAbrirConta.add(campoIdRegistrarCliente);
        painelAbrirConta.add(new JLabel("Nome Cliente"));
        painelAbrirConta.add(campoNomeCliente);
        painelAbrirConta.add(new JLabel("Idade Cliente"));
        painelAbrirConta.add(campoIdadeCliente);
        painelAbrirConta.add(new JLabel("Email Cliente"));
        painelAbrirConta.add(campoEmailCliente);
        painelAbrirConta.add(new JLabel("Ativo:"));
        painelAbrirConta.add(ativoClienteCheck);
        painelAbrirConta.add(new JLabel("ID Conta"));
        painelAbrirConta.add(campoIdContaRegistrar);
        painelAbrirConta.add(new JLabel("Saldo Inicial:"));
        painelAbrirConta.add(campoSaldo);
        painelAbrirConta.add(new JLabel("Ativa:"));
        painelAbrirConta.add(ativoContaCheck);
        painelAbrirConta.add(new JLabel(""));
        painelAbrirConta.add(btnConfirmar);
        painelAbrirConta.add(btnVoltarAbrirConta);
        
        this.add(painelAbrirConta);
	}
	
	public void setListenerBtnConfirmar(ActionListener listener) {
		this.btnConfirmar.addActionListener(listener);
	}
	
	public void setListenerBtnVoltar(ActionListener listener) {
		this.btnVoltarAbrirConta.addActionListener(listener);
	}
	
	public String getIdCliente() {
		return this.campoIdRegistrarCliente.getText();
	}
	
	public String getNomeCliente() {
		return this.campoNomeCliente.getText();
	}
	
	public String getIdadeCliente() {
		return this.campoIdadeCliente.getText();
	}
	
	public String getEmail() {
		return this.campoEmailCliente.getText();
	}
	
	public boolean getAtivoCliente() {
		return this.ativoClienteCheck.isSelected();
	}
	
	public String getIdConta() {
		return this.campoIdContaRegistrar.getText();
	}
	
	public String getSaldo() {
		return this.campoSaldo.getText();
	}
	
	public boolean getAtivoConta() {
		return this.ativoContaCheck.isSelected();
	}
	
	public void limparCampos() {
		this.campoIdRegistrarCliente.setText("");
		this.campoNomeCliente.setText("");
		this.campoIdadeCliente.setText("");
		this.campoEmailCliente.setText("");
		this.ativoClienteCheck.setSelected(false);
		this.campoIdContaRegistrar.setText("");
		this.campoSaldo.setText("");
		this.ativoContaCheck.setSelected(false);
	}
	
	public JPanel getPanel() {
		return this;
	}
}
