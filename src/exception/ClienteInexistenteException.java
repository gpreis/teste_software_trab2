package exception;

public class ClienteInexistenteException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ClienteInexistenteException(String msg) {
		super(msg);
	}
}
