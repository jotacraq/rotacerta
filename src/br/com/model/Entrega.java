package br.com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Entrega {

    private int id;
    private String codigo;
    private String cep;
    private String estado;
    private String status;
    private Timestamp dataEntrega;

    private Entregador entregador; // 👈 RELACIONAMENTO

    private Entrega() {}

    public static Entrega fromDatabase(ResultSet rs) throws SQLException {
        Entrega e = new Entrega();

        e.codigo = rs.getString("codigo");
        e.cep = rs.getString("cep");
        e.estado = rs.getString("estado");
        e.status = rs.getString("status");
        e.dataEntrega = rs.getTimestamp("data_entrega");

        e.entregador = Entregador.fromDatabase(
                rs.getInt("entregador_id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getInt("idade")
        );

        return e;
    }

    public static Entrega paraInsercao(String codigo, String cep, String estado) {
        Entrega e = new Entrega();
        e.codigo = codigo;
        e.cep = cep;
        e.estado = estado;
        e.status = StatusEntrega.PENDENTE.name();
        return e;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Timestamp dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
