package com.javatechie.spring.okta.sso.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	 // @GetMapping("/")
	  //  public String home(@AuthenticationPrincipal OidcUser user) {
	       // return "Welcome, "+ user.getFullName() +"!";
	   // }

}
