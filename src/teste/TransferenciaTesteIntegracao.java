package teste;

import negocio.GerenciadoraContas;
import negocio.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransferenciaTesteIntegracao {
    private GerenciadoraContas gerenciadoraContas;
    private ContaCorrente conta1;
    private ContaCorrente conta2;

    @BeforeEach
    void setUp() {
        List<ContaCorrente> contas = new ArrayList<>();
        conta1 = new ContaCorrente(1, 1000.0, true);
        conta2 = new ContaCorrente(2, 500.0, true);
        contas.add(conta1);
        contas.add(conta2);
        gerenciadoraContas = new GerenciadoraContas(contas);
    }

    @Test
    void testThreeTransfersBetweenTwoAccounts() {
        // First transfer: 100 from conta1 to conta2
        boolean success1 = gerenciadoraContas.transfereValor(1, 100.0, 2);
        assertTrue(success1);
        assertEquals(900.0, conta1.getSaldo(), 0.01);
        assertEquals(600.0, conta2.getSaldo(), 0.01);

        // Second transfer: 200 from conta2 to conta1
        boolean success2 = gerenciadoraContas.transfereValor(2, 200.0, 1);
        assertTrue(success2);
        assertEquals(1100.0, conta1.getSaldo(), 0.01);
        assertEquals(400.0, conta2.getSaldo(), 0.01);

        // Third transfer: 50 from conta1 to conta2
        boolean success3 = gerenciadoraContas.transfereValor(1, 50.0, 2);
        assertTrue(success3);
        assertEquals(1050.0, conta1.getSaldo(), 0.01);
        assertEquals(450.0, conta2.getSaldo(), 0.01);
    }
}