package view.interfaces;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface TelaMenuInterface {
	
	public void setListenerBtnAbrirConta(ActionListener listener);
	
	public void setListenerBtnConsultarConta(ActionListener listener);
	
	public void setListenerBtnConsultarCliente(ActionListener listener);
	
	public void setListenerBtnExcluir(ActionListener listener);
	
	public void setListenerBtnAbrirTransferencia(ActionListener listener);
	
	public JPanel getPanel();
}
