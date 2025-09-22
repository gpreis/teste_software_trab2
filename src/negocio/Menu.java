package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private GerenciadoraClientes gerClientes;
	private GerenciadoraContas gerContas;
	
	public Menu() {
		
	}

	Scanner sc = new Scanner(System.in);
	boolean continua = true;
		
	public void menu_principal() {
	while (continua){
			
			printMenu();
			
			int opcao = sc.nextInt();
			
			switch (opcao) {
			// Consultar por um cliente
			case 1:
				System.out.print("Digite o ID do cliente: ");
				int idCliente = sc.nextInt();
				Cliente cliente = gerClientes.pesquisaCliente(idCliente);
				
				if(cliente != null)
					System.out.println(cliente.toString());
				else
					System.out.println("Cliente n�o encontrado!");
				
				pulalinha();
				break;

			// Consultar por uma conta corrente
			case 2:
				System.out.print("Digite o ID da conta: ");
				int idConta = sc.nextInt();
				ContaCorrente conta = gerContas.pesquisaConta(idConta);
				
				if(conta != null)
					System.out.println(conta.toString());
				else
					System.out.println("Conta n�o encontrado!");
				
				pulalinha();
				break;

			// Ativar um cliente
			case 3:
				
				System.out.print("Digite o ID do cliente: ");
				int idCliente2 = sc.nextInt();
				Cliente cliente2 = gerClientes.pesquisaCliente(idCliente2);
				
				if(cliente2 != null){
					cliente2.setAtivo(true);
					System.out.println("Cliente ativado com sucesso!");
				}
				else
					System.out.println("Cliente n�o encontrado!");
			
				pulalinha();
				break;
				
			// Desativar um cliente
			case 4:
				
				System.out.print("Digite o ID do cliente: ");
				int idCliente3 = sc.nextInt();
				Cliente cliente3 = gerClientes.pesquisaCliente(idCliente3);
				
				if(cliente3 != null){
					cliente3.setAtivo(false);
					System.out.println("Cliente desativado com sucesso!");
				}
				else
					System.out.println("Cliente n�o encontrado!");
				
				pulalinha();
				break;
			
			// Transferir
			case 5:
				System.out.println("Informe o id da conta origem: ");
				int id_origem = sc.nextInt();
				System.out.println("Informe o id da conta destino: ");
				int id_destino = sc.nextInt();
				System.out.println("Informe o valor de tranferência: ");
				float valor = sc.nextFloat();
				
				if(this.gerContas.transfereValor(id_origem, valor, id_destino)) {
					System.out.println("Transferência realizada com sucesso!");
				}
				else {
					System.out.println("Transferência fracassada.");
				}
				
				break;
			//sair
			case 6:
				continua = false;
				System.out.println("################# Sistema encerrado #################");
				break;
		
			default:
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				break;
				
			} 
		}
	sc.close();
	}
	
	

	private void pulalinha() {
		System.out.println("\n");
	}

	/**
	 * Imprime menu de op��es do nosso sistema banc�rio
	 */
	private void printMenu() {
		
		System.out.println("O que voc� deseja fazer? \n");
		System.out.println("1) Consultar por um cliente");
		System.out.println("2) Consultar por uma conta corrente");
		System.out.println("3) Ativar um cliente");
		System.out.println("4) Desativar um cliente");
		System.out.println("5) Relizar transferência");
		System.out.println("6) Sair");
		System.out.println();
		
	}

	/**
	 * M�todo que cria e insere algumas contas e clientes no sistema do banco,
	 * apenas para realiza��o de testes manuais atrav�s do m�todo main acima.
	 */
	public void inicializaSistemaBancario() {
		// criando lista vazia de contas e clientes
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		List<Cliente> clientesDoBanco = new ArrayList<>();
		
		// criando e inserindo duas contas na lista de contas correntes do banco
		ContaCorrente conta01 = new ContaCorrente(3, 100.0, false);
		ContaCorrente conta02 = new ContaCorrente(2, 200, true);
		System.out.println(conta01.getSaldo());
		System.out.println(conta02.getSaldo());
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		// criando dois clientes e associando as contas criadas acima a eles
		Cliente cliente01 = new Cliente(1, "Maria Silva", 31, "mariasilva@gmail.com", conta01.getId(), true);
		Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", conta02.getId(), true);
		// inserindo os clientes criados na lista de clientes do banco
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
		gerContas = new GerenciadoraContas(contasDoBanco);
		
	}
	 
	
}

