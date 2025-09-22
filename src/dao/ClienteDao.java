package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import util.Conexao;

public class ClienteDao implements ClienteRepository{

    public boolean inserir(Cliente cliente) {
        String sql = "INSERT INTO cliente (id, nome, idade, email, ativo, idContaCorrente) VALUES (?, ?, ?, ?, ?, ?)";
        boolean flag = false;;
        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
        	stmt.setInt(1, cliente.getId());
            stmt.setString(2, cliente.getNome());
            stmt.setInt(3, cliente.getIdade());
            stmt.setString(4, cliente.getEmail());
            stmt.setBoolean(5, cliente.isAtivo());
            
            
            if (cliente.getIdContaCorrente() > 0) {
                stmt.setInt(6, cliente.getIdContaCorrente());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            stmt.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente.");
            e.printStackTrace();
        }
        return flag;
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = Conexao.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setEmail(rs.getString("email"));
                cliente.setAtivo(rs.getBoolean("ativo"));
                cliente.setIdContaCorrente(rs.getInt("idContaCorrente"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes.");
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        Cliente cliente = null;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setIdade(rs.getInt("idade"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setAtivo(rs.getBoolean("ativo"));
                    cliente.setIdContaCorrente(rs.getInt("idContaCorrente"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente.");
            e.printStackTrace();
        }

        return cliente;
    }
    
    public Cliente buscarPorIdConta(int id) {
        String sql = "SELECT * FROM cliente WHERE idContaCorrente = ?";
        Cliente cliente = null;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setIdade(rs.getInt("idade"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setAtivo(rs.getBoolean("ativo"));
                    cliente.setIdContaCorrente(rs.getInt("idContaCorrente"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente.");
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        boolean removido = false;

        try (Connection con = Conexao.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            int linhas = stmt.executeUpdate();
            removido = linhas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente.");
            e.printStackTrace();
        }

        return removido;
    }
}
