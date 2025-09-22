package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ContaCorrente;
import util.Conexao;

public class ContaCorrenteDao implements ContaCorrenteRepository{
   
    public List<ContaCorrente> getContasDoBanco() {
        List<ContaCorrente> contas = new ArrayList<>();
        String sql = "SELECT * FROM conta";

        try (Connection con = Conexao.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ContaCorrente conta = new ContaCorrente();
                conta.setId(rs.getInt("id"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setAtiva(rs.getBoolean("ativa"));
                contas.add(conta);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar contas.");
            e.printStackTrace();
        }

        return contas;
    }

   
    public ContaCorrente buscarPorId(int idConta) {
        String sql = "SELECT * FROM conta WHERE id = ?";
        ContaCorrente conta = null;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    conta = new ContaCorrente();
                    conta.setId(rs.getInt("id"));
                    conta.setSaldo(rs.getDouble("saldo"));
                    conta.setAtiva(rs.getBoolean("ativa"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar conta.");
            e.printStackTrace();
        }

        return conta;
    }

    public boolean inserir(ContaCorrente novaConta) {
    	 String sql = "INSERT INTO conta (id, saldo, ativa) VALUES (?, ?, ?)";
    	 boolean flag = false;
         try (Connection con = Conexao.getConnection();
              PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	 
        	 stmt.setInt(1, novaConta.getId());
             stmt.setDouble(2, novaConta.getSaldo());
             stmt.setBoolean(3, novaConta.isAtiva());
             stmt.executeUpdate();
             flag = true;
            
         }
         catch (SQLException e) {
             System.out.println("Erro ao inserir conta.");
             e.printStackTrace();
         }   	
        return flag;
    }

    public boolean remover(int idConta) {
        String sql = "DELETE FROM conta WHERE id = ?";
        boolean removido = false;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, idConta);
            int linhas = stmt.executeUpdate();
            removido = linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover conta.");
            e.printStackTrace();
        }
        return removido;
    }

    public boolean contaAtiva(int idConta) {
        ContaCorrente conta = buscarPorId(idConta);
        return conta != null && conta.isAtiva();
    }

    public boolean transfereValor(int idContaOrigem, int idContaDestino, double valor) {
        ContaCorrente contaOrigem = buscarPorId(idContaOrigem);
        ContaCorrente contaDestino = buscarPorId(idContaDestino);

        if (contaOrigem != null && contaDestino != null && contaOrigem.getSaldo() >= valor) {
            // Atualiza saldo da conta de origem
            atualizarSaldo(contaOrigem.getId(), contaOrigem.getSaldo() - valor);
            // Atualiza saldo da conta de destino
            atualizarSaldo(contaDestino.getId(), contaDestino.getSaldo() + valor);
            return true;
        }

        return false;
    }

    // Método auxiliar para atualizar saldo
    private boolean atualizarSaldo(int idConta, double novoSaldo) {
        String sql = "UPDATE conta SET saldo = ? WHERE id = ?";
        boolean atualizado = false;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDouble(1, novoSaldo);
            stmt.setInt(2, idConta);
            int linhas = stmt.executeUpdate();
            atualizado = linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar saldo.");
            e.printStackTrace();
        }

        return atualizado;
    }
}


