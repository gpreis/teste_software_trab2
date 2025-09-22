package teste;

import org.junit.Before;
import org.junit.Test;

import controller.ClienteController;
import exception.IdadeNaoPermitidaException;
import model.Cliente;

import static org.junit.Assert.*;
import java.util.*;

public class ClienteControllerTeste {

    private ClienteController ger;

    @Before
    public void setUp() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "A", 20, "a@a.com", 1, true));
        clientes.add(new Cliente(2, "B", 30, "b@b.com", 2, false));
        ger = new ClienteController(null);
    }

    @Test
    public void testPesquisaCliente() {
        assertNotNull(ger.pesquisaCliente(1));
        assertNull(ger.pesquisaCliente(99));
    }

    @Test
    public void testAdicionaCliente() {
        Cliente c = new Cliente(3, "C", 40, "c@c.com", 3, true);
        try {
			ger.adicionaCliente(c);
		} catch (IdadeNaoPermitidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals(3, ger.getClientesDoBanco().size());
        assertEquals(c, ger.pesquisaCliente(3));
    }

    @Test
    public void testRemoveCliente() {
        assertTrue(ger.removeCliente(1));
        assertFalse(ger.removeCliente(99));
    }

    @Test
    public void testClienteAtivo() {
        assertTrue(ger.clienteAtivo(1));
        assertFalse(ger.clienteAtivo(2));
    }   

    @Test
    public void testValidaIdadeValida() throws Exception {
        assertTrue(ger.validaIdade(18));
        assertTrue(ger.validaIdade(65));
    }

    @Test(expected = Exception.class)
    public void testValidaIdadeInvalidaBaixa() throws Exception {
        ger.validaIdade(17);
    }

    @Test(expected = Exception.class)
    public void testValidaIdadeInvalidaAlta() throws Exception {
        ger.validaIdade(66);
    }
}