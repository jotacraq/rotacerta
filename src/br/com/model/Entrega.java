package br.com.model;

import java.sql.Timestamp;

public class Entrega {

    private int id;
    private Integer entregadorId;
    private String codigo;
    private String cep;
    private String estado;
    private String status;
    private Timestamp dataEntrega;

    private Entrega() {
    }

    public static Entrega fromDatabase(int id, int entregadorId, String codigo, String cep, String estado, String status, Timestamp dataEntrega) {
        Entrega e = new Entrega();
        e.id = id;
        e.entregadorId = entregadorId;
        e.codigo = codigo;
        e.cep = cep;
        e.estado = estado;
        e.status = status;
        e.dataEntrega = dataEntrega;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getEntregadorId() {
        return entregadorId;
    }

    public void setEntregadorId(Integer entregadorId) {
        this.entregadorId = entregadorId;
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
}