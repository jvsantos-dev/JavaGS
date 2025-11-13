package com.gs.CareerBooster.repository;

import com.gs.CareerBooster.exception.DataBaseConnectionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataBaseConnection {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public Connection getConnection() {
        try (Connection conn = DriverManager.getConnection(url, username, password)){
            System.out.println("Conectado com sucesso");
            return conn;
        } catch (SQLException e){
            throw new DataBaseConnectionException("Falha ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}
