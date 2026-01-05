package br.com.service;

import br.com.model.Entrega;
import br.com.repository.EntregaRepository;

import java.awt.*;
import java.util.List;

public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public void cadastrarEntrega(Entrega entrega) {

        if (entrega.getCep().length() > 8) {
            throw new IllegalArgumentException("ERRO: TAMANHO INVÁLIDO DE CEP!");
        }

        repository.inserir(entrega);
    }

    public List<Entrega> listarTodasEntregas() {
        return repository.buscarTodos();
    }

    public void associarEntregador(String codigoEntrega, int entregadorId) {

        if (codigoEntrega == null || codigoEntrega.isBlank()) {
            throw new IllegalArgumentException("ERRO: CÓDIGO DE ENTREGA INVÁLIDO!");
        }

        repository.associarEntregador(codigoEntrega, entregadorId);
    }

    public void finalizarEntregar(String codigoEntregaFinal) {

        if (codigoEntregaFinal == null || codigoEntregaFinal.isBlank()) {
            throw new IllegalArgumentException("ERRO: CÓDIGO DE ENTREGA INVÁLIDO!");
        }

        repository.finalizarEntrega(codigoEntregaFinal);
    }

    public void cancelarEntrega(String codigoEntregaAtiva) {

        if (codigoEntregaAtiva == null || codigoEntregaAtiva.isBlank()) {
            throw new IllegalArgumentException("ERRO: CÓDIGO DE ENTREGA INVÁLIDO!");
        }

        repository.excluirEntrega(codigoEntregaAtiva);
    }

}
