package br.com.repository;

import br.com.model.Entrega;
import br.com.model.StatusEntrega;

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
            INSERT INTO entregas (codigo, cep, logradouro, bairro, estado, status)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entrega.getCodigo());
            stmt.setString(2, entrega.getCep());
            stmt.setString(3, entrega.getLogradouro());
            stmt.setString(4, entrega.getBairro());
            stmt.setString(5, entrega.getEstado());
            stmt.setString(6, entrega.getStatus());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao registrar a entrega", e);
        }
    }


    public List<Entrega> buscarTodos() {

        String sql = """
        SELECT
            e.entregador_id,
            e.codigo,
            e.cep,
            e.logradouro,
            e.bairro,
            e.estado,
            e.status,
            e.data_entrega,
            en.nome,
            en.cpf,
            en.idade
        FROM entregas e
        LEFT JOIN entregadores en ON en.id = e.entregador_id
    """;

        List<Entrega> entregas = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Entrega entrega = Entrega.fromDatabase(rs);
                entregas.add(entrega);
            }

        } catch (SQLException ex) {
            throw new RuntimeException("ERRO: Falha ao listar entregas", ex);
        }

        return entregas;
    }

    public void associarEntregador(String codigoEntrega, int entregadorId) {

        String sql = """
                UPDATE entregas
                SET entregador_id = ?, status = ?
                WHERE codigo = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, entregadorId);
            stmt.setString(2, StatusEntrega.EM_ROTA.name());
            stmt.setString(3, codigoEntrega);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao associar entregador à entrega", e);
        }
    }

    public void finalizarEntrega(String codigoEntregaFinal) {

        String sql = """
                UPDATE entregas
                SET status = ?
                WHERE codigo = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, StatusEntrega.ENTREGUE.name());
            stmt.setString(2, codigoEntregaFinal);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao finalizar a entrega", e);
        }

    }

    public void excluirEntrega(String codigoEntregaAtiva) {

        String sql = """
                DELETE FROM entregas WHERE codigo = ?
                """;

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoEntregaAtiva);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("ERRO: Falha ao cancelar a entrega", e);
        }
    }

    public Integer buscarEntregadorIdPorCodigo(String codigoEntrega) {

        String sql = """
            SELECT entregador_id
            FROM entregas
            WHERE codigo = ?
            """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigoEntrega);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("entregador_id");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "ERRO: Falha ao consultar entregador da entrega", e
            );
        }

        return 0; // não tem entregador
    }


}
