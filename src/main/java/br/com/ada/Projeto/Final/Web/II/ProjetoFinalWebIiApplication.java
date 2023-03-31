package br.com.ada.Projeto.Final.Web.II;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class ProjetoFinalWebIiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalWebIiApplication.class, args);
	}

}
