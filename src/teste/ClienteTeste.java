package teste;

import org.junit.Test;

import model.Cliente;

import static org.junit.Assert.*;

public class ClienteTeste {

    @Test
    public void testGettersAndSetters() {
        Cliente c = new Cliente(1, "Joao", 25, "joao@email.com", 10, true);
        assertEquals(1, c.getId());
        assertEquals("Joao", c.getNome());
        assertEquals(25, c.getIdade());
        assertEquals("joao@email.com", c.getEmail());
        assertEquals(10, c.getIdContaCorrente());
        assertTrue(c.isAtivo());

        c.setId(2);
        c.setNome("Maria");
        c.setIdade(30);
        c.setEmail("maria@email.com");
        c.setIdContaCorrente(20);
        c.setAtivo(false);

        assertEquals(2, c.getId());
        assertEquals("Maria", c.getNome());
        assertEquals(30, c.getIdade());
        assertEquals("maria@email.com", c.getEmail());
        assertEquals(20, c.getIdContaCorrente());
        assertFalse(c.isAtivo());
    }

    @Test
    public void testToString() {
        Cliente c = new Cliente(1, "Joao", 25, "joao@email.com", 10, true);
        String str = c.toString();
        assertTrue(str.contains("Joao"));
        assertTrue(str.contains("Ativo"));
    }
}