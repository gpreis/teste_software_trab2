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
public class TelaConsultaConta extends JPanel implements TelaConsultaInterface{
	
	private JPanel painelConsultarConta;
    
    private JTextField campoIdConta;
    
	private JTextArea areaDetalhesConta;
	
	private JButton btnBuscarConta;
	private JButton btnVoltarConsultarConta;
	
	public TelaConsultaConta() {
		
		painelConsultarConta = new JPanel(new BorderLayout(5,5));
        JPanel topoConta = new JPanel(new GridLayout(1,2,5,5));
        campoIdConta = new JTextField();
        btnBuscarConta = new JButton("Buscar");
        this.btnVoltarConsultarConta = new JButton("Voltar");
        topoConta.add(new JLabel("ID da Conta"));
        topoConta.add(campoIdConta);
        topoConta.add(new JLabel(""));
        topoConta.add(btnBuscarConta);
        topoConta.add(btnVoltarConsultarConta);
        
        areaDetalhesConta = new JTextArea();
        areaDetalhesConta.setEditable(false);
        JScrollPane scrollConta = new JScrollPane(areaDetalhesConta);
        scrollConta.setPreferredSize(new Dimension(500, 400));
        painelConsultarConta.add(topoConta, BorderLayout.NORTH);
        painelConsultarConta.add(scrollConta, BorderLayout.CENTER);
        this.add(painelConsultarConta);
	}
	
	public void setListenerBtnConfirmar(ActionListener listener) {
		this.btnBuscarConta.addActionListener(listener);
	}
	
	public void setListenerBtnVoltar(ActionListener listener) {
		this.btnVoltarConsultarConta.addActionListener(listener);
	}
	
	public String getId() {
		return this.campoIdConta.getText();
	}
	
	public void setAreaDetalhes(String detalhe) {
		this.areaDetalhesConta.setText(detalhe);
	}
	
	public void limparCampos() {
		this.campoIdConta.setText("");
		this.areaDetalhesConta.setText("");
	}
	
	public JPanel getPanel() {
		return this;
	}
}
