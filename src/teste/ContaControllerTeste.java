package teste;

import org.junit.Before;
import org.junit.Test;

import controller.ContaCorrenteController;
import model.ContaCorrente;

import static org.junit.Assert.*;
import java.util.*;

public class ContaControllerTeste {

    private ContaCorrenteController ger;
    private ContaCorrente c1, c2;

    @Before
    public void setUp() {
       
        c1 = new ContaCorrente();
        c1.setId(1);
        c1.setSaldo(100.0);
        c1.setAtiva(true);

        c2 = new ContaCorrente();
        c2.setId(2);
        c2.setSaldo(200.0);
        c2.setAtiva(false);

        List<ContaCorrente> contas = new ArrayList<>();
        contas.add(c1);
        contas.add(c2);

        ger = new ContaCorrenteController();
    }

    @Test
    public void testPesquisaConta() {
        assertEquals(c1, ger.pesquisaConta(1));
        assertNull(ger.pesquisaConta(99));
    }

    @Test
    public void testAdicionaConta() {
        ContaCorrente c3 = new ContaCorrente();
        c3.setId(3);
        c3.setSaldo(300.0);
        c3.setAtiva(true);

        ger.adicionaConta(c3);

        assertEquals(3, ger.getContasDoBanco().size());
        assertEquals(c3, ger.pesquisaConta(3));
    }

    @Test
    public void testRemoveConta() {
        assertTrue(ger.removeConta(1));
        assertFalse(ger.removeConta(99));
    }

    @Test
    public void testContaAtiva() {
        assertTrue(ger.contaAtiva(1));
        assertFalse(ger.contaAtiva(2));
    }

    @Test
    public void testTransfereValorSucesso() {
        boolean result = ger.transfereValor(1, 50.0, 2);
        assertTrue(result);
        assertEquals(50.0, c1.getSaldo(), 0.001);
        assertEquals(250.0, c2.getSaldo(), 0.001);
    }

    @Test
    public void testTransfereValorFalha() {
        boolean result = ger.transfereValor(1, 200.0, 2);
        assertFalse(result);
        assertEquals(100.0, c1.getSaldo(), 0.001);
        assertEquals(200.0, c2.getSaldo(), 0.001);
    }
}
