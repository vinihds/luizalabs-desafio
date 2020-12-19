package br.com.luizalabs.desafio.cep.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.luizalabs.desafio.cep.api",
		"br.com.luizalabs.desafio.cep.core",
		"br.com.luizalabs.desafio.cep.service"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
