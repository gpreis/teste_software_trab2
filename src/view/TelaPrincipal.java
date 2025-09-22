package view;

import controller.ClienteController;
import controller.ContaCorrenteController;
import model.Cliente;
import model.ContaCorrente;
import javax.swing.*;
import java.awt.*;


public class TelaPrincipal extends JFrame {

    private ClienteController clienteController;
    private ContaCorrenteController contaController;

    private JPanel painelCentral;
    private CardLayout cardLayout;

    public TelaPrincipal(ClienteController clienteController, ContaCorrenteController contaController) {
        super("Banco UCS");
        this.clienteController = clienteController;
        this.contaController = contaController;

        configurarTela();
    }

    private void configurarTela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Título ---
        JLabel titulo = new JLabel("Banco UCS", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // --- Painel central com CardLayout ---
        cardLayout = new CardLayout();
        painelCentral = new JPanel(cardLayout);
        add(painelCentral, BorderLayout.CENTER);

        // --- Tela inicial com 3 opções ---
        JPanel painelInicial = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton btnAbrirConta = new JButton("Abrir Conta");
        JButton btnConsultarConta = new JButton("Consultar Conta");
        JButton btnConsultarCliente = new JButton("Consultar Cliente");

        btnAbrirConta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnConsultarConta.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnConsultarCliente.setFont(new Font("SansSerif", Font.PLAIN, 18));

        painelInicial.add(btnAbrirConta);
        painelInicial.add(btnConsultarConta);
        painelInicial.add(btnConsultarCliente);

        // --- Tela Abrir Conta ---
        JPanel painelAbrirConta = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField campoSaldo = new JTextField();
        JCheckBox ativaCheck = new JCheckBox("Ativa", true);
        JButton btnCriarConta = new JButton("Criar Conta");

        painelAbrirConta.add(new JLabel("Saldo Inicial:"));
        painelAbrirConta.add(campoSaldo);
        painelAbrirConta.add(new JLabel("Ativa:"));
        painelAbrirConta.add(ativaCheck);
        painelAbrirConta.add(new JLabel(""));
        painelAbrirConta.add(btnCriarConta);

        // --- Tela Consultar Conta ---
        JPanel painelConsultarConta = new JPanel(new GridLayout(0, 2, 5, 5));
        JTextField campoIdConta = new JTextField();
        JTextArea areaDetalhesConta = new JTextArea();
        areaDetalhesConta.setEditable(false);
        JScrollPane scrollConta = new JScrollPane(areaDetalhesConta);
        JButton btnBuscarConta = new JButton("Buscar");

        painelConsultarConta.add(new JLabel("ID da Conta:"));
        painelConsultarConta.add(campoIdConta);
        painelConsultarConta.add(new JLabel(""));
        painelConsultarConta.add(btnBuscarConta);
        painelConsultarConta.add(scrollConta);

        // --- Tela Consultar Cliente ---
        JPanel painelConsultarCliente = new JPanel(new BorderLayout(5,5));
        JPanel topoCliente = new JPanel(new GridLayout(1,2,5,5));
        JTextField campoIdCliente = new JTextField();
        JButton btnBuscarCliente = new JButton("Buscar");
        topoCliente.add(new JLabel("ID do Cliente:"));
        topoCliente.add(campoIdCliente);
        topoCliente.add(new JLabel(""));
        topoCliente.add(btnBuscarCliente);

        JTextArea areaDetalhesCliente = new JTextArea();
        areaDetalhesCliente.setEditable(false);
        JScrollPane scrollCliente = new JScrollPane(areaDetalhesCliente);

        painelConsultarCliente.add(topoCliente, BorderLayout.NORTH);
        painelConsultarCliente.add(scrollCliente, BorderLayout.CENTER);

        // --- Adicionar painéis ao CardLayout ---
        painelCentral.add(painelInicial, "INICIAL");
        painelCentral.add(painelAbrirConta, "ABRIR_CONTA");
        painelCentral.add(painelConsultarConta, "CONSULTAR_CONTA");
        painelCentral.add(painelConsultarCliente, "CONSULTAR_CLIENTE");

        // --- Ações dos botões ---
        btnAbrirConta.addActionListener(e -> cardLayout.show(painelCentral, "ABRIR_CONTA"));
        btnConsultarConta.addActionListener(e -> cardLayout.show(painelCentral, "CONSULTAR_CONTA"));
        btnConsultarCliente.addActionListener(e -> cardLayout.show(painelCentral, "CONSULTAR_CLIENTE"));

        btnCriarConta.addActionListener(e -> {
            try {
                double saldo = Double.parseDouble(campoSaldo.getText());
                boolean ativa = ativaCheck.isSelected();
                ContaCorrente nova = new ContaCorrente();
                nova.setSaldo(saldo);
                nova.setAtiva(ativa);
                contaController.adicionaConta(nova);
                JOptionPane.showMessageDialog(this, "Conta criada com sucesso! ID: " + nova.getId());
                campoSaldo.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Saldo inválido!");
            }
        });

        btnBuscarConta.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoIdConta.getText());
                ContaCorrente conta = contaController.pesquisaConta(id);
                if(conta != null) {
                    areaDetalhesConta.setText(
                            "ID: " + conta.getId() + "\n" +
                            "Saldo: " + conta.getSaldo() + "\n" +
                            "Ativa: " + conta.isAtiva()
                    );
                } else {
                    areaDetalhesConta.setText("Conta não encontrada.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!");
            }
        });

        btnBuscarCliente.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoIdCliente.getText());
                Cliente c = clienteController.pesquisaCliente(id);
                if(c != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ID: ").append(c.getId()).append("\n");
                    sb.append("Nome: ").append(c.getNome()).append("\n");
                    sb.append("Idade: ").append(c.getIdade()).append("\n");
                    sb.append("Email: ").append(c.getEmail()).append("\n");
                    areaDetalhesCliente.setText(sb.toString());
                } else {
                    areaDetalhesCliente.setText("Cliente não encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido!");
            }
        });

        cardLayout.show(painelCentral, "INICIAL");
    }
}
