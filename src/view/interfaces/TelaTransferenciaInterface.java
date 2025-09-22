package view.interfaces;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface TelaTransferenciaInterface {
	
	public void setListenerBtnConfirmar(ActionListener listener);
	
	public void setListenerBtnVoltar(ActionListener listener);
	
	public String getIdContaOrigem();
	
	public String getIdContaDestino();
	
	public String getValor();
	
	public void setAreaDetalhes(String detalhe);
	
	public void limparCampos();
	
	public JPanel getPanel();
}