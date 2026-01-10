package br.com.model;

public class Entregador {

    // Dados do entregador
    private Integer id;
    private String nome;
    private String cpf;
    private int idade;

    private Entregador() {
    }

    // Cria um entregador a partir dos dados do banco
    public static Entregador fromDatabase(int id, String nome, String cpf, int idade) {
        Entregador entregador = new Entregador();
        entregador.id = id;
        entregador.nome = nome;
        entregador.cpf = cpf;
        entregador.idade = idade;
        return entregador;
    }

    // Cria um entregador para inserção no banco
    public static Entregador paraInsercao(String nome, String cpf, int idade) {
        Entregador entregador = new Entregador();
        entregador.nome = nome;
        entregador.cpf = cpf;
        entregador.idade = idade;
        return entregador;
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
