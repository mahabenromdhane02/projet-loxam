package com.javatechie.spring.okta.sso.controller;

import com.javatechie.spring.okta.sso.dao.Applicationinformations;
import com.javatechie.spring.okta.sso.repository.ApplicationRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class ApplicationController {

    @GetMapping("/listusers")
    public String consolePage(){
        return "listapp";
    }

}