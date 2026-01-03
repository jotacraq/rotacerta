package br.com.model;

import java.sql.Timestamp;

public class Entregador {

    private Integer id;
    private String nome;
    private String cpf;
    private int idade;

    private Entregador() {
    }

    public static Entregador fromDatabase(int id, String nome, String cpf, int idade) {
        Entregador e = new Entregador();
        e.id = id;
        e.nome = nome;
        e.cpf = cpf;
        e.idade = idade;
        return e;
    }


    public static Entregador paraInsercao(String nome, String cpf, int idade) {
        Entregador e = new Entregador();
        e.nome = nome;
        e.cpf = cpf;
        e.idade = idade;
        return e;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
