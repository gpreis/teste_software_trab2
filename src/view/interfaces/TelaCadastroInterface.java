package view.interfaces;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface TelaCadastroInterface {
	
	public void setListenerBtnConfirmar(ActionListener listener);
	
	public void setListenerBtnVoltar(ActionListener listener);
	
	public String getIdCliente();
	
	public String getNomeCliente();
	
	public String getIdadeCliente();
	public String getEmail();
	
	public boolean getAtivoCliente();
	
	public String getIdConta();
	
	public String getSaldo();
	
	public boolean getAtivoConta();
	
	public void limparCampos();
	
	public JPanel getPanel();
}