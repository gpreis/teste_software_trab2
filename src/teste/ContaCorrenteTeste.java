package teste;

import org.junit.Test;

import model.ContaCorrente;

import static org.junit.Assert.*;

public class ContaCorrenteTeste {

    @Test
    public void testGettersAndSetters() {
        ContaCorrente cc = new ContaCorrente(1, 100.0, true);
        assertEquals(1, cc.getId());
        assertEquals(100.0, cc.getSaldo(), 0.001);
        assertTrue(cc.isAtiva());

        cc.setId(2);
        cc.setSaldo(200.0);
        cc.setAtiva(false);

        assertEquals(2, cc.getId());
        assertEquals(200.0, cc.getSaldo(), 0.001);
        assertFalse(cc.isAtiva());
    }

    @Test
    public void testToString() {
        ContaCorrente cc = new ContaCorrente(1, 100.0, true);
        String str = cc.toString();
        assertTrue(str.contains("Id: 1"));
        assertTrue(str.contains("Ativa"));
    }
}