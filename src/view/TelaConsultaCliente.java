package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.interfaces.TelaConsultaInterface;

@SuppressWarnings("serial")
public class TelaConsultaCliente extends JPanel implements TelaConsultaInterface{
	
		private JPanel painelConsultarCliente;
	  
	    private JTextField campoIdCliente;
	
	    private JTextArea areaDetalhesCliente;
	    
	    private JButton btnVoltarConsultarCliente;
	    private JButton btnConfirmar;
	
	public TelaConsultaCliente() {
		
		painelConsultarCliente = new JPanel(new BorderLayout(5,5));
        JPanel topoCliente = new JPanel(new GridLayout(1,2,5,5));
        campoIdCliente = new JTextField();
        btnConfirmar = new JButton("Buscar");
        this.btnVoltarConsultarCliente = new JButton("Voltar");
        topoCliente.add(new JLabel("ID do Cliente:"));
        topoCliente.add(campoIdCliente);
        topoCliente.add(new JLabel(""));
        topoCliente.add(btnConfirmar);
        topoCliente.add(btnVoltarConsultarCliente);

        areaDetalhesCliente = new JTextArea();
        areaDetalhesCliente.setEditable(false);
        JScrollPane scrollCliente = new JScrollPane(areaDetalhesCliente);
        scrollCliente.setPreferredSize(new Dimension(500, 400));
        painelConsultarCliente.add(topoCliente, BorderLayout.NORTH);
        painelConsultarCliente.add(scrollCliente, BorderLayout.CENTER);
        
        this.add(painelConsultarCliente);
	}
	
	public void setListenerBtnConfirmar(ActionListener listener) {
		this.btnConfirmar.addActionListener(listener);
	}
	
	public void setListenerBtnVoltar(ActionListener listener) {
		this.btnVoltarConsultarCliente.addActionListener(listener);
	}
	
	public String getId() {
		return this.campoIdCliente.getText();
	}
	
	public void setAreaDetalhes(String detalhe) {
		this.areaDetalhesCliente.setText(detalhe);
	}
	
	public void limparCampos() {
		this.campoIdCliente.setText("");
		this.areaDetalhesCliente.setText("");
	}
	
	public JPanel getPanel() {
		return this;
	}
}
