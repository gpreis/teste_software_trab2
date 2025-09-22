package negocio;

public class Main {

	private Menu menu = new Menu();
	
	public static void main(String[] args) {
		
		Main app = new Main();
		app.iniciar();
		
	}
	
	private void iniciar() {
		this.menu.inicializaSistemaBancario();
		this.menu.menu_principal();
	}
}
