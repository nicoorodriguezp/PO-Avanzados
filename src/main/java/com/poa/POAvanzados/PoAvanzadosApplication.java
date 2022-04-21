package com.poa.POAvanzados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import Model.UserModel.User;

@SpringBootApplication
public class PoAvanzadosApplication {

	public User user;
	public static void main(String[] args) {
		SpringApplication.run(PoAvanzadosApplication.class, args);
	}

}
