package teste;

import negocio.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

public class MenuTeste {

    private Menu menu;

    @Before
    public void setUp() {
        menu = new Menu();
    }

    @Test
    public void testPrintMenu() {
        Menu menu = new Menu();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Acessar método privado via reflexão
        try {
            java.lang.reflect.Method printMenu = Menu.class.getDeclaredMethod("printMenu");
            printMenu.setAccessible(true);
            printMenu.invoke(menu);
        } catch (Exception e) {
            fail("Exception during reflection: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("O que voc"));
        assertTrue(output.contains("1) Consultar por um cliente"));
        assertTrue(output.contains("6) Sair"));
    }

    @Test
    public void testPulalinha() {
        Menu menu = new Menu();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        // Acessar método privado via reflexão
        try {
            java.lang.reflect.Method pulalinha = Menu.class.getDeclaredMethod("pulalinha");
            pulalinha.setAccessible(true);
            pulalinha.invoke(menu);
        } catch (Exception e) {
            fail("Exception during reflection: " + e.getMessage());
        } finally {
            System.setOut(originalOut);
        }

        String output = outContent.toString();
        assertTrue(output.contains("\n"));
    }

    @Test
    public void testInicializaSistemaBancario() throws Exception {
        menu.inicializaSistemaBancario();

        // Acessar campos privado via reflexão
        Field gerClientesField = Menu.class.getDeclaredField("gerClientes");
        Field gerContasField = Menu.class.getDeclaredField("gerContas");
        gerClientesField.setAccessible(true);
        gerContasField.setAccessible(true);

        GerenciadoraClientes gerClientes = (GerenciadoraClientes) gerClientesField.get(menu);
        GerenciadoraContas gerContas = (GerenciadoraContas) gerContasField.get(menu);

        assertNotNull(gerClientes);
        assertNotNull(gerContas);

        List<Cliente> clientes = gerClientes.getClientesDoBanco();
        List<ContaCorrente> contas = gerContas.getContasDoBanco();

        assertEquals(2, clientes.size());
        assertEquals(2, contas.size());

        Cliente cliente1 = clientes.get(0);
        Cliente cliente2 = clientes.get(1);

        assertEquals("Maria Silva", cliente1.getNome());
        assertEquals("Felipe Augusto", cliente2.getNome());

        ContaCorrente conta1 = contas.get(0);
        ContaCorrente conta2 = contas.get(1);

        assertEquals(3, conta1.getId());
        assertEquals(2, conta2.getId());
    }
}

