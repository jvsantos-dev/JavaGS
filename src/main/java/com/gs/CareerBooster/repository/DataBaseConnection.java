package com.gs.CareerBooster.repository;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataBaseConnection {
    public static void main(String[] args){
        String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl";
        String username = "RM557948";
        String password = "180405";


        try (Connection conn = DriverManager.getConnection(url, username, password)){
            if (conn != null) {
                System.out.println("Conectado com sucesso");
            }
        } catch (SQLException e){
            System.out.println("Erro na conexao" + e.getMessage());
        }
    }
}
