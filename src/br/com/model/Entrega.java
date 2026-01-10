package br.com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entrega {

    // Dados da entrega
    private int id;
    private String codigo;
    private String cep;
    private String logradouro;
    private String bairro;
    private String estado;
    private String status;
    private Timestamp dataEntrega;

    // Entregador responsável
    private Entregador entregador;

    private Entrega() {
    }

    // Cria uma entrega a partir do ResultSet
    public static Entrega fromDatabase(ResultSet rs) throws SQLException {
        Entrega entrega = new Entrega();

        entrega.id = rs.getInt("id");
        entrega.codigo = rs.getString("codigo");
        entrega.cep = rs.getString("cep");
        entrega.logradouro = rs.getString("logradouro");
        entrega.bairro = rs.getString("bairro");
        entrega.estado = rs.getString("estado");
        entrega.status = rs.getString("status");
        entrega.dataEntrega = rs.getTimestamp("data_entrega");

        entrega.entregador = Entregador.fromDatabase(
                rs.getInt("entregador_id"),
                rs.getString("nome"),
                rs.getString("cpf"),
                rs.getInt("idade")
        );

        return entrega;
    }

    // Cria uma entrega para inserção no banco
    public static Entrega paraInsercao(String cep) {
        Entrega entrega = new Entrega();
        entrega.cep = cep;
        entrega.status = StatusEntrega.PENDENTE.name();
        return entrega;
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
