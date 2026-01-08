package br.com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrega {

    private int id;
    private String codigo;
    private String cep;
    private String logradouro;
    private String bairro;
    private String estado;
    private String status;
    private Timestamp dataEntrega;

    private Entregador entregador;

    private Entrega() {}

    public static Entrega fromDatabase(ResultSet rs) throws SQLException {
        Entrega e = new Entrega();

        e.codigo = rs.getString("codigo");
        e.cep = rs.getString("cep");
        e.estado = rs.getString("estado");
        e.logradouro = rs.getString("logradouro");
        e.bairro = rs.getString("bairro");
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

    public static Entrega paraInsercao(String cep) {
        Entrega e = new Entrega();
        e.cep = cep;
        e.status = StatusEntrega.PENDENTE.name();
        // logradouro, bairro e estado serão preenchidos pelo service da API da ViaCEP
        return e;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
