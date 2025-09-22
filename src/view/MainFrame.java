package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.interfaces.MainFrameInterface;

@SuppressWarnings("serial")
public class MainFrame  extends JFrame implements MainFrameInterface{
	
	private CardLayout cardLayout;
	private JPanel mainPanel;
	
	public MainFrame() {
		setTitle("Sistema Bancario");
		setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.cardLayout = new CardLayout();
		this.mainPanel = new JPanel(this.cardLayout);
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	public void addPanel(JPanel panel, String nome) {
		this.mainPanel.add(panel, nome);
	}
	
	public void showTela(String tela) {
		this.cardLayout.show(mainPanel, tela);
	}
}
