package br.com.service;

import br.com.model.Entregador;
import br.com.repository.EntregadorRepository;

import java.util.List;

public class EntregadorService {

    private final EntregadorRepository repository;

    public EntregadorService(EntregadorRepository repository) {
        this.repository = repository;
    }

    public void cadastrarEntregador(Entregador entregador) {

        if (entregador.getCpf().length() > 11) {
            throw new IllegalArgumentException("ERRO: Tamanho inválido de CPF!");
        }

        repository.inserir(entregador);
    }

    public List<Entregador> listarTodosEntregadores() {
        return repository.buscarTodos();
    }
}
