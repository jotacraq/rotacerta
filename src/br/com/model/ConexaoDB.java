package br.com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/rotacerta";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco", e);
        }
    }
}

