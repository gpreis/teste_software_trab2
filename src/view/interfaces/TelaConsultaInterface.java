package view.interfaces;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

public interface TelaConsultaInterface {
	
	public void setListenerBtnConfirmar(ActionListener listener);
	
	public void setListenerBtnVoltar(ActionListener listener);
	
	public String getId();
	
	public void setAreaDetalhes(String detalhe);

	public void limparCampos();
	
	public JPanel getPanel();
}
