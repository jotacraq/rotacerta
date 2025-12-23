package br.com.app;

import br.com.model.ConexaoDB;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Fiz só pra testar a conexão

        try (Connection conn = ConexaoDB.conectar()) {
            System.out.println("Conectado");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar conexão", e);
        }
    }
}
