package main;

import controller.ClienteController;
import controller.ContaCorrenteController;
import controller.MasterController;
import dao.ClienteDao;
import dao.ContaCorrenteDao;
import service.ClienteServiceImpl;
import service.ContaCorrenteServiceImpl;
import util.SetupBancoDeDados;
import view.MainFrame;
import view.TelaCadastro;
import view.TelaConsultaCliente;
import view.TelaConsultaConta;
import view.TelaExcluir;
import view.TelaMenu;
import view.TelaTransferencia;

public class Main {

    public static void main(String[] args) {
    	
    	
    	//criar o banco SistemaBancario e depois descomentar para criar as tabelas...apos criadas comentar novamente
    	//SetupBancoDeDados setup = new SetupBancoDeDados();
    	//setup.criarTabelas();
    	
    	
    	ClienteDao clienteDao = new ClienteDao();
        ContaCorrenteDao contaDao = new ContaCorrenteDao();
        
        ClienteServiceImpl clienteService = new ClienteServiceImpl(clienteDao);
        ContaCorrenteServiceImpl contaService = new ContaCorrenteServiceImpl(contaDao);
    	
    	
    	ClienteController clienteController = new ClienteController(clienteService);
        ContaCorrenteController contaController = new ContaCorrenteController(contaService);
        
        MainFrame mainFrame = new MainFrame();
        TelaMenu telaMenu = new TelaMenu();
        TelaCadastro telaCadastro = new TelaCadastro();
        TelaConsultaConta telaConta = new TelaConsultaConta();
        TelaConsultaCliente telaCliente = new TelaConsultaCliente();
        TelaExcluir telaExcluir = new TelaExcluir();
        TelaTransferencia telaTransferencia = new TelaTransferencia();
       
    	MasterController master = new MasterController(clienteController, contaController, mainFrame, telaMenu, telaCadastro, 
    			telaConta, telaCliente, telaExcluir, telaTransferencia);
    	mainFrame.setVisible(true);
        
    }
}
