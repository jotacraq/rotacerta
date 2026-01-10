package br.com.repository;

import br.com.model.Entregador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregadorRepository {

    private final Connection conn;

    public EntregadorRepository(Connection connection) {
        this.conn = connection;
    }

    // Insere um novo entregador no banco
    public void inserir(Entregador entregador) {
        String sql = """
            INSERT INTO entregadores (nome, cpf, idade)
            VALUES (?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entregador.getNome());
            stmt.setString(2, entregador.getCpf());
            stmt.setInt(3, entregador.getIdade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao registrar entregador", e);
        }
    }

    // Lista todos os entregadores
    public List<Entregador> buscarTodos() {
        String sql = "SELECT id, nome, cpf, idade FROM entregadores";

        List<Entregador> entregadores = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entregador entregador = Entregador.fromDatabase(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade")
                );

                entregadores.add(entregador);
            }

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao listar entregadores", e);
        }

        return entregadores;
    }
}

