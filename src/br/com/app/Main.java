package br.com.app;

import br.com.model.ConexaoDB;
import br.com.model.Entregador;
import br.com.model.Entrega;
import br.com.repository.EntregaRepository;
import br.com.repository.EntregadorRepository;
import br.com.service.EntregaService;
import br.com.service.EntregadorService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        try (Connection connection = ConexaoDB.conectar()) {

            EntregadorRepository entregadorRepository = new EntregadorRepository(connection);
            EntregadorService entregadorService = new EntregadorService(entregadorRepository);

            EntregaRepository entregaRepository = new EntregaRepository(connection);
            EntregaService entregaService = new EntregaService(entregaRepository);

            while (true) {
                System.out.println("-----------------------------------");
                System.out.println("ENTREGADORES:");
                System.out.println("1. Cadastrar Entregador");
                System.out.println("2. Listar Entregadores");
                System.out.println("-----------------------------------");
                System.out.println("ENTREGAS:");
                System.out.println("3. Registrar Entrega");
                System.out.println("4. Listar Entregas");
                System.out.println("5. Associar Entregador à Entrega");
                System.out.println("6. Finalizar Entrega");
                System.out.println("7. Cancelar Entrega");
                System.out.println("8. Sair");
                System.out.println("-----------------------------------");
                System.out.print("DIGITE SUA ESCOLHA: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("ENTRADA INVÁLIDA, DIGITE UM NÚMERO!");
                    scanner.next();
                    continue;
                }

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    // ---- OPÇÕES PARA ENTREGADOR ----

                    case 1:
                        System.out.print("NOME: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("IDADE: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();

                        Entregador entregador = Entregador.paraInsercao(nome, cpf, idade);

                        entregadorService.cadastrarEntregador(entregador);
                        System.out.println("ENTREGADOR CADASTRADO!");
                        break;

                    case 2:
                        List<Entregador> entregadores = entregadorService.listarTodosEntregadores();

                        System.out.println("\n=== ENTREGADORES ===\n");

                        for (Entregador e : entregadores) {
                            System.out.println(
                                    "ID: " + e.getId() +
                                            " | Nome: " + e.getNome() +
                                            " | CPF: " + e.getCpf() +
                                            " | Idade: " + e.getIdade()
                            );
                        }

                        System.out.println("\nPRESSIONE ENTER PARA CONTINUAR...");
                        scanner.nextLine();
                        break;

                    // ---- OPÇÕES PARA ENTREGA ----

                    case 3:
                        System.out.print("CÓDIGO: ");
                        String codigo = scanner.nextLine();

                        System.out.print("CEP: ");
                        String cep = scanner.nextLine();

                        System.out.print("ESTADO: ");
                        String estado = scanner.nextLine();

                        Entrega entrega = Entrega.paraInsercao(codigo, cep, estado);

                        entregaService.cadastrarEntrega(entrega);
                        System.out.println("ENTREGA REGISTRADA!");
                        break;

                    case 4:
                        List<Entrega> entregas = entregaService.listarTodasEntregas();

                        DateTimeFormatter formatter =
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                        System.out.println("\n=== LISTA DE ENTREGAS ===\n");

                        for (Entrega e : entregas) {

                            String dataFormatada =
                                    e.getDataEntrega() != null
                                            ? e.getDataEntrega().toLocalDateTime().format(formatter)
                                            : "-";

                            System.out.println(
                                    "Código: " + e.getCodigo() +
                                            " | CEP: " + e.getCep() +
                                            " | Estado: " + e.getEstado() +
                                            " | Status: " + e.getStatus() +
                                            " | Data: " + dataFormatada +
                                            " | Entregador: " + e.getEntregador().getNome() +
                                            " | CPF: " + e.getEntregador().getCpf()
                            );
                        }

                        System.out.println("\nPRESSIONE ENTER PARA CONTINUAR...");
                        scanner.nextLine();
                        break;

                    case 5:
                        System.out.print("CÓDIGO DA ENTREGA: ");
                        String codigoEntrega = scanner.nextLine();

                        System.out.print("ID DO ENTREGADOR: ");
                        int entregadorId = scanner.nextInt();
                        scanner.nextLine();

                        entregaService.associarEntregador(codigoEntrega, entregadorId);

                        System.out.println("ENTREGADOR ASSOCIADO À ENTREGA!");
                        break;

                    case 6:
                        System.out.print("CODIGO DA ENTREGA: ");
                        String codigoEntregaFinal = scanner.nextLine();

                        entregaService.finalizarEntregar(codigoEntregaFinal);

                        System.out.println("ENTREGA FINALIZADA!");
                        break;

                    case 7:
                        System.out.print("CODIGO DA ENTREGA: ");
                        String codigoEntregaAtiva = scanner.nextLine();

                        entregaService.cancelarEntrega(codigoEntregaAtiva);

                        System.out.println("ENTREGA CANCELADA!");
                        break;

                    case 8:
                        System.out.println("SAINDO DO PROGRAMA...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }
            }
        }
    }
}
