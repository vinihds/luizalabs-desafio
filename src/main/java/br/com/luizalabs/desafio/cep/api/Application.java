package br.com.luizalabs.desafio.cep.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"br.com.luizalabs.desafio.cep.core.client"})
@SpringBootApplication(scanBasePackages = {"br.com.luizalabs.desafio.cep"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
