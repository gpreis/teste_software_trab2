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

import view.interfaces.TelaTransferenciaInterface;

@SuppressWarnings("serial")
public class TelaTransferencia extends JPanel implements TelaTransferenciaInterface{
	    
	    private JPanel painelTransferencia;
	    
	    private JTextField campoIdContaOrigem;
	    private JTextField campoIdContaDestino;
	    private JTextField campoValorTransferencia;
	    
	    private JTextArea areaDetalhesTransferencia;
	    
	    private JButton btnTransferir;
	    private JButton btnVoltarTransferencia;
	    
	public TelaTransferencia() {
		
		painelTransferencia = new JPanel(new BorderLayout(5,5));

        JPanel topoTransferencia = new JPanel(new GridLayout(4,2,5,5));
        campoIdContaOrigem = new JTextField();
        campoIdContaDestino = new JTextField();
        campoValorTransferencia = new JTextField();

        btnTransferir = new JButton("Transferir");
        btnVoltarTransferencia = new JButton("Voltar");

        topoTransferencia.add(new JLabel("ID Conta Origem:"));
        topoTransferencia.add(campoIdContaOrigem);

        topoTransferencia.add(new JLabel("ID Conta Destino:"));
        topoTransferencia.add(campoIdContaDestino);

        topoTransferencia.add(new JLabel("Valor da Transferência:"));
        topoTransferencia.add(campoValorTransferencia);

        topoTransferencia.add(btnTransferir);
        topoTransferencia.add(btnVoltarTransferencia);

        areaDetalhesTransferencia = new JTextArea();
        areaDetalhesTransferencia.setEditable(false);
        JScrollPane scrollTransferencia = new JScrollPane(areaDetalhesTransferencia);
        scrollTransferencia.setPreferredSize(new Dimension(500, 400));
        painelTransferencia.add(topoTransferencia, BorderLayout.NORTH);
        painelTransferencia.add(scrollTransferencia, BorderLayout.CENTER);
        
        this.add(painelTransferencia);
	}
	
	public void setListenerBtnConfirmar(ActionListener listener) {
		this.btnTransferir.addActionListener(listener);
	}
	
	public void setListenerBtnVoltar(ActionListener listener) {
		this.btnVoltarTransferencia.addActionListener(listener);
	}
	
	public String getIdContaOrigem() {
		return this.campoIdContaOrigem.getText();
	}
	
	public String getIdContaDestino() {
		return this.campoIdContaDestino.getText();
	}
	
	public String getValor() {
		return this.campoValorTransferencia.getText();
	}
	
	public void setAreaDetalhes(String detalhe) {
		this.areaDetalhesTransferencia.setText(detalhe);
	}
	
	public void limparCampos() {
		this.campoIdContaOrigem.setText("");
		this.campoIdContaDestino.setText("");
		this.campoValorTransferencia.setText("");
		this.areaDetalhesTransferencia.setText("");
	}
	
	public JPanel getPanel() {
		return this;
	}
}
