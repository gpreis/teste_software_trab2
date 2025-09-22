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
public class TelaExcluir extends JPanel implements TelaConsultaInterface{

    private JPanel painelExcluirClienteConta;

    private JTextField campoIdExcluirClienteConta;

    private JTextArea areaDetalhesExcluirClienteConta;
    
    private JButton btnExcluirClienteConta;
    private JButton btnVoltarExcluirClienteConta;

	public TelaExcluir() {
		
		painelExcluirClienteConta = new JPanel(new BorderLayout(5,5));
        JPanel topoExcluirClienteConta = new JPanel(new GridLayout(1,2,5,5));

        campoIdExcluirClienteConta = new JTextField();
        btnExcluirClienteConta = new JButton("Excluir");
        this.btnVoltarExcluirClienteConta = new JButton("Voltar");

        topoExcluirClienteConta.add(new JLabel("ID do Cliente:"));
        topoExcluirClienteConta.add(campoIdExcluirClienteConta);
        topoExcluirClienteConta.add(new JLabel(""));
        topoExcluirClienteConta.add(btnExcluirClienteConta);
        topoExcluirClienteConta.add(btnVoltarExcluirClienteConta);

        areaDetalhesExcluirClienteConta = new JTextArea();
        areaDetalhesExcluirClienteConta.setEditable(false);
        JScrollPane scrollExcluirClienteConta = new JScrollPane(areaDetalhesExcluirClienteConta);
        scrollExcluirClienteConta.setPreferredSize(new Dimension(500, 400));
        painelExcluirClienteConta.add(topoExcluirClienteConta, BorderLayout.NORTH);
        painelExcluirClienteConta.add(scrollExcluirClienteConta, BorderLayout.CENTER);
        
        this.add(painelExcluirClienteConta);
	}
	
	public void setListenerBtnConfirmar(ActionListener listener) {
		this.btnExcluirClienteConta.addActionListener(listener);
	}
	
	public void setListenerBtnVoltar(ActionListener listener) {
		this.btnVoltarExcluirClienteConta.addActionListener(listener);
	}
	
	public String getId() {
		return this.campoIdExcluirClienteConta.getText();
	}
	
	public void setAreaDetalhes(String detalhes) {
		this.areaDetalhesExcluirClienteConta.setText(detalhes);
	}
	
	public void limparCampos() {
		this.campoIdExcluirClienteConta.setText("");
		this.areaDetalhesExcluirClienteConta.setText("");
	}
	
	public JPanel getPanel() {
		return this;
	}
}
