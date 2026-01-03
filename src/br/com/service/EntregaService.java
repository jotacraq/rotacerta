package br.com.service;

import br.com.model.Entrega;
import br.com.repository.EntregaRepository;

import java.util.List;

public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public void cadastrarEntrega(Entrega entrega) {

        if (entrega.getCep().length() > 8) {
            throw new IllegalArgumentException("ERRO: Tamanho inválido de CEP!");
        }

        repository.inserir(entrega);
    }

    public List<Entrega> listarTodasEntregas() {
        return repository.buscarTodos();
    }
}
