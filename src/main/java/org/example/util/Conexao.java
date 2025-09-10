package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/Sistema_De_Logistica?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String SENHA = "mysqlPW";


    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, SENHA);
    }
}