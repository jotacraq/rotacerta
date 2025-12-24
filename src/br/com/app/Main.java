package br.com.app;

import br.com.model.ConexaoDB;
import br.com.model.Entregador;
import br.com.repository.EntregadorRepository;
import br.com.service.EntregadorService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        try (Connection connection = ConexaoDB.conectar()) {

            EntregadorRepository repository =
                    new EntregadorRepository(connection);

            EntregadorService service =
                    new EntregadorService(repository);

            while (true) {
                System.out.println("-----------------------------------");
                System.out.println("ENTREGADORES:");
                System.out.println("1. Cadastrar Entregador");
                System.out.println("2. Listar Entregadores");
                System.out.println("3. Sair");
                System.out.println("-----------------------------------");
                System.out.print("DIGITE SUA ESCOLHA: ");

                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            scanner.nextLine();

                            System.out.print("NOME: ");
                            String nome = scanner.nextLine();

                            System.out.print("CPF: ");
                            String cpf = scanner.nextLine();

                            System.out.print("IDADE: ");
                            int idade = scanner.nextInt();

                            Entregador entregador = new Entregador(nome, cpf, idade);
                            service.cadastrar(entregador);
                            break;

                        case 2:
                            List<Entregador> entregadores = service.listarTodos();
                            System.out.println("\n");
                            for (Entregador e : entregadores) {
                                System.out.println(
                                        e.getId() + " | " +
                                                e.getNome() + " | " +
                                                e.getCpf() + " | " +
                                                e.getIdade()
                                );
                            }

                            scanner.nextLine();
                            System.out.println("\nPRESSIONE ENTER PARA SAIR.");
                            scanner.nextLine();

                            break;
                        case 3:
                            System.out.println("SAINDO DO PROGRAMA!");
                            scanner.close();
                            System.exit(0);
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA!");
                    }
                } else {
                    System.out.println("ENTRADA INVÁLIDA, DIGITE UM NÚMERO!");
                    scanner.next();
                }
                System.out.println("\n");
            }


        }



    }
}
