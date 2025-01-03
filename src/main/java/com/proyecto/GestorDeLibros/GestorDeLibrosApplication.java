package com.proyecto.GestorDeLibros;

import com.proyecto.GestorDeLibros.Principal.Principal;
import com.proyecto.GestorDeLibros.repository.ClaseLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestorDeLibrosApplication implements CommandLineRunner {

	@Autowired
	private ClaseLibroRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(GestorDeLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal main = new Principal(repository);
		main.getMenu();
	}
}
