package br.com.luizalabs.desafio.cep.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EntityScan(basePackages = {"br.com.luizalabs.desafio.cep.core.entity"})
@EnableJpaRepositories(basePackages = {"br.com.luizalabs.desafio.cep.core.repository"})
@SpringBootApplication(scanBasePackages = {"br.com.luizalabs.desafio.cep"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
