package main;

import javax.swing.SwingUtilities;

import controller.ClienteController;
import controller.ContaCorrenteController;
import dao.ClienteDao;
import dao.ContaDao;
import util.SetupBancoDeDados;
import view.TelaPrincipal;

public class Main {

    public static void main(String[] args) {
    	
    	
    	/* criar o banco SistemaBancario e depois descomentar para criar as tabelas...apos criadas comentar novamente
    	SetupBancoDeDados setup=new SetupBancoDeDados();
    	setup.criarTabelas();*/
        
        ClienteDao clienteDao = new ClienteDao();
        ContaDao contaDao = new ContaDao();
       
        ClienteController clienteController = new ClienteController(clienteDao);
        ContaCorrenteController contaController = new ContaCorrenteController(contaDao);
       
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal(clienteController, contaController);
            tela.setVisible(true);
        });
    }
}
