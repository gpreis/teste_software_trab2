package teste;

import controller.ClienteController;
import controller.ContaCorrenteController;
import dao.ClienteRepository;
import dao.ContaCorrenteRepository;
import exception.ClienteJaCadastradoException;
import exception.ContaJaCadastradaException;
import exception.ContaInexistenteException;
import exception.IdadeNaoPermitidaException;
import exception.SaldoInsuficienteException;
import model.Cliente;
import model.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ClienteServiceImpl;
import service.ContaCorrenteServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransferenciaTesteIntegracao {
    // ClienteRepository em memória
    static class EmMemoriaClienteRepository implements ClienteRepository {
        private final Map<Integer, Cliente> clientes = new HashMap<>();
        @Override public Cliente buscarPorId(int id) { return clientes.get(id); }
        @Override public Cliente buscarPorIdConta(int idConta) {
            return clientes.values().stream().filter(c -> c.getIdContaCorrente() == idConta).findFirst().orElse(null);
        }
        @Override public boolean inserir(Cliente cliente) { clientes.put(cliente.getId(), cliente); return true; }
        @Override public boolean remover(int id) { return clientes.remove(id) != null; }
        @Override public List<Cliente> listar() { return new ArrayList<>(clientes.values()); }
    }
    // ContaCorrenteRepository em memória
    static class EmMemoriaContaCorrenteRepository implements ContaCorrenteRepository {
        private final Map<Integer, ContaCorrente> contas = new HashMap<>();
        @Override public ContaCorrente buscarPorId(int id) { return contas.get(id); }
        @Override public boolean inserir(ContaCorrente conta) { contas.put(conta.getId(), conta); return true; }
        @Override public boolean remover(int id) { return contas.remove(id) != null; }
        @Override public boolean contaAtiva(int id) { ContaCorrente c = contas.get(id); return c != null && c.isAtiva(); }
        @Override public boolean transfereValor(int idOrigem, int idDestino, double valor) {
            ContaCorrente origem = contas.get(idOrigem);
            ContaCorrente destino = contas.get(idDestino);
            if (origem == null || destino == null) return false;
            if (origem.getSaldo() < valor) return false;
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            return true;
        }
        @Override public List<ContaCorrente> getContasDoBanco() { return new ArrayList<>(contas.values()); }
    }

    private ClienteController clienteController;
    private ContaCorrenteController contaController;
    private EmMemoriaClienteRepository clienteRepo;
    private EmMemoriaContaCorrenteRepository contaRepo;

    @BeforeEach
    void setUp() {
        clienteRepo = new EmMemoriaClienteRepository();
        contaRepo = new EmMemoriaContaCorrenteRepository();
        clienteController = new ClienteController(new ClienteServiceImpl(clienteRepo));
        contaController = new ContaCorrenteController(new ContaCorrenteServiceImpl(contaRepo));
    }

    @Test
    void testTransferenciaEntreContasViaController() throws Exception {
        // Adicionar dois clientes e suas contas
        List<String> cliente1 = Arrays.asList("1", "Alice", "30", "alice@email.com", "101");
        List<String> cliente2 = Arrays.asList("2", "Bob", "40", "bob@email.com", "102");
        clienteController.criarCliente(cliente1, true);
        clienteController.criarCliente(cliente2, true);
        contaController.criarContaCorrente(101, 1000.0, true);
        contaController.criarContaCorrente(102, 500.0, true);

        // Transferir 100 de Alice para Bob
        contaController.transferir(101, 102, 100.0);
        assertEquals(900.0, contaRepo.buscarPorId(101).getSaldo(), 0.01);
        assertEquals(600.0, contaRepo.buscarPorId(102).getSaldo(), 0.01);

        // Transferir 200 de Bob para Alice
        contaController.transferir(102, 101, 200.0);
        assertEquals(1100.0, contaRepo.buscarPorId(101).getSaldo(), 0.01);
        assertEquals(400.0, contaRepo.buscarPorId(102).getSaldo(), 0.01);

        // Transferir 50 de Alice para Bob
        contaController.transferir(101, 102, 50.0);
        assertEquals(1050.0, contaRepo.buscarPorId(101).getSaldo(), 0.01);
        assertEquals(450.0, contaRepo.buscarPorId(102).getSaldo(), 0.01);
    }
}