package com.poa.POAvanzados;

import com.poa.POAvanzados.Database.MongoDB.Entities.UserRepository;
import com.poa.POAvanzados.Model.UserModel.User;
import com.poa.POAvanzados.Service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PoAvanzadosApplication {

	public User user;

	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(PoAvanzadosApplication.class, args);
	}

	@Bean
	CommandLineRunner createUser(UserRepository repo) {
		return args -> {

			try {
				User adminUser = new User(
						41476258,
						0,
						0,
						"Nicolas",
						"Rodriguez",
						"nicogrodriguezp@gmail.com");

				repo.insert(adminUser);
			} catch (Exception e) {
				System.out.println("El usuario ya existe.");
			}

		};
	}

	@Bean
	CommandLineRunner getUser(UserRepository repo) {
		return args -> {

			int idUser = 41476258;

			userService = new UserService(repo);
			User user = userService.getUser(idUser);
			System.out.println("Se encontro el usuario: " + user);

		};
	}

}
