package com.javatechie.spring.okta.sso;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;
@EnableOAuth2Sso
@SpringBootApplication
public class SpringBootOktaSsoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootOktaSsoApplication.class, args);
	}

	@GetMapping("/")
	public String welcome2User(Principal principal) {
		return "Hi "+principal.getName()+" Welcome to okta";
	}
}


