package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupBancoDeDados {
    
    public void criarTabelas() {
        tabelaConta();   
        tabelaCliente();
    }

    public void tabelaCliente() {
        String sql = """
            CREATE TABLE IF NOT EXISTS cliente (
                id SERIAL PRIMARY KEY,
                nome VARCHAR(100) NOT NULL,
                idade INT,
                email VARCHAR(100),
                ativo BOOLEAN,
                idContaCorrente INT,
                CONSTRAINT fk_conta FOREIGN KEY (idContaCorrente) REFERENCES conta(id)
            )
        """;

        try (Connection con = Conexao.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela Cliente criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela Cliente");
            e.printStackTrace();
        }
    }

    public void tabelaConta() {
        String sql = """
            CREATE TABLE IF NOT EXISTS conta (
                id SERIAL PRIMARY KEY,
                saldo DECIMAL(15,2) DEFAULT 0,
                ativa BOOLEAN
            )
        """;

        try (Connection con = Conexao.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela Conta criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela Conta");
            e.printStackTrace();
        }
    }
}
