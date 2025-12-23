package br.com.model;

public class Entrega {

    private Integer id;
    private String codigo;
    private String cep;
    private String estado;
    private StatusEntrega status = StatusEntrega.PENDENTE;
    private Entregador entregador;

    // Construtor pra quando o objeto vir do sql

    public Entrega(Integer id, String codigo, String cep, String estado, StatusEntrega status, Entregador entregador) {
        this.id = id;
        this.codigo = codigo;
        this.cep = cep;
        this.estado = estado;
        this.status = status;
        this.entregador = entregador;
    }

    // Construtor pra quando eu enviar o objeto pelo insert

    public Entrega(String codigo, String cep, String estado, Entregador entregador) {
        this.codigo = codigo;
        this.cep = cep;
        this.estado = estado;
        this.entregador = entregador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }
}
