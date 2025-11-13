package com.gs.CareerBooster;

import com.gs.CareerBooster.dao.DataBaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CareerBoosterApplication {

	public static void main(String[] args) {

		SpringApplication.run(CareerBoosterApplication.class, args);
	}

}
