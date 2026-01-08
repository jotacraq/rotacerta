package br.com.service;

import br.com.model.Entrega;
import br.com.repository.EntregaRepository;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EntregaService {

    private final EntregaRepository repository;

    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }

    public void cadastrarEntrega(Entrega entrega) {

        if (entrega.getCep() == null || entrega.getCep().length() != 8) {
            throw new IllegalArgumentException("ERRO: TAMANHO INVÁLIDO DE CEP!");
        }

        try {
            Entrega dadosCep = viaCepService.puxarDadosCEP(entrega.getCep());

            entrega.setLogradouro(dadosCep.getLogradouro());
            entrega.setBairro(dadosCep.getBairro());
            entrega.setEstado(dadosCep.getEstado());

            repository.inserir(entrega);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("ERRO: Falha ao buscar CEP na API", e);
        }
    }

    public List<Entrega> listarTodasEntregas() {
        return repository.buscarTodos();
    }

    public void associarEntregadorID(String codigoEntrega, int entregadorId) {

        if (codigoEntrega == null || codigoEntrega.isBlank()) {
            throw new IllegalArgumentException("ERRO: CÓDIGO DE ENTREGA INVÁLIDO!");
        }

        Integer entregadorExistente =
                repository.buscarEntregadorIdPorCodigo(codigoEntrega);

        if (entregadorExistente != 0) {
            throw new IllegalStateException(
                    "ERRO: ESTA ENTREGA JÁ POSSUI ENTREGADOR ASSOCIADO!"
            );
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
