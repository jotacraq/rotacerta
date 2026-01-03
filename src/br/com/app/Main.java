package br.com.app;

import br.com.model.ConexaoDB;
import br.com.model.Entregador;
import br.com.model.Entrega;
import br.com.model.Entregador;
import br.com.model.ConexaoDB;
import br.com.repository.EntregaRepository;
import br.com.repository.EntregadorRepository;
import br.com.service.EntregaService;
import br.com.service.EntregadorService;

import java.sql.Connection;
import java.sql.SQLException;
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
                System.out.println("5. Sair");
                System.out.println("-----------------------------------");
                System.out.print("DIGITE SUA ESCOLHA: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("ENTRADA INVÁLIDA, DIGITE UM NÚMERO!");
                    scanner.next();
                    continue;
                }

                int opcao = scanner.nextInt();
                scanner.nextLine(); // consumir Enter

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

                        System.out.println("\nID | NOME | CPF | IDADE");
                        for (Entregador e : entregadores) {
                            System.out.println(
                                    e.getId() + " | " +
                                            e.getNome() + " | " +
                                            e.getCpf() + " | " +
                                            e.getIdade()
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

                        System.out.println("\nID | CÓDIGO | CEP | ESTADO | STATUS | DATA");
                        for (Entrega e : entregas) {
                            System.out.println(
                                    e.getId() + " | " +
                                            e.getCodigo() + " | " +
                                            e.getCep() + " | " +
                                            e.getEstado() + " | " +
                                            e.getStatus() + " | " +
                                            e.getDataEntrega()
                            );
                        }

                        System.out.println("\nPRESSIONE ENTER PARA CONTINUAR...");
                        scanner.nextLine();
                        break;

                    case 5:
                        System.out.println("SAINDO DO PROGRAMA!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("OPÇÃO INVÁLIDA!");
                }
            }
        }
    }
}
