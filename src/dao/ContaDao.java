package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ContaCorrente;
import util.Conexao;

public class ContaDao {
   
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

   
    public ContaCorrente pesquisaConta(int idConta) {
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

    public void adicionaConta(ContaCorrente novaConta) {
    	 String sql = "INSERT INTO conta (saldo, ativa) VALUES (?, ?)";

         try (Connection con = Conexao.getConnection();
              PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

             stmt.setDouble(1, novaConta.getSaldo());
             stmt.setBoolean(2, novaConta.isAtiva());
             stmt.executeUpdate();
            
             try (ResultSet rs = stmt.getGeneratedKeys()) {
                 if (rs.next()) {
                     novaConta.setId(rs.getInt(1));
                 }
             }

         } catch (SQLException e) {
             System.out.println("Erro ao inserir conta.");
             e.printStackTrace();
         }   	
        
    }

    public boolean removeConta(int idConta) {
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
        ContaCorrente conta = pesquisaConta(idConta);
        return conta != null && conta.isAtiva();
    }

    public boolean transfereValor(int idContaOrigem, double valor, int idContaDestino) {
        ContaCorrente contaOrigem = pesquisaConta(idContaOrigem);
        ContaCorrente contaDestino = pesquisaConta(idContaDestino);

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


