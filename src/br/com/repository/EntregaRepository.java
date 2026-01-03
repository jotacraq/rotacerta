package br.com.repository;

import br.com.model.Entrega;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaRepository {

    private Connection conn;

    public EntregaRepository(Connection connection) {
        this.conn = connection;
    }

    public void inserir(Entrega entrega) {

        String sql = """
        INSERT INTO entregas (codigo, cep, estado, status)
        VALUES (?, ?, ?, ?)
    """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entrega.getCodigo());
            stmt.setString(2, entrega.getCep());
            stmt.setString(3, entrega.getEstado());
            stmt.setString(4, entrega.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao registrar entrega", e);
        }
    }


    public List<Entrega> buscarTodos() {

        String sql = """
        SELECT id, entregador_id, codigo, cep, estado, status, data_entrega
        FROM entregas
    """;

        List<Entrega> entregas = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Entrega e = Entrega.fromDatabase(
                        rs.getInt("id"),
                        rs.getInt("entregador_id"),
                        rs.getString("codigo"),
                        rs.getString("cep"),
                        rs.getString("estado"),
                        rs.getString("status"),
                        rs.getTimestamp("data_entrega")
                );

                entregas.add(e);
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar entregas", e);
        }

        return entregas;
    }
}
