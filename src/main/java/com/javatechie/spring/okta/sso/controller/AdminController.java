package com.javatechie.spring.okta.sso.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.okta.sdk.resource.user.UserBuilder;
import com.okta.sdk.resource.user.UserList;

@RestController
public class AdminController {
	  @Autowired
	    public Client client;
	  
	    @GetMapping("/users")
	    public UserList getUsers() {
	        return client.listUsers();
	    }
	    
	    //@GetMapping("/user")
	    //public UserList searchUserByEmail(@RequestParam String mail) {
	        //return client.listUsers(mail, null, null, null, null);
	    //}
	    
	    //@RequestMapping(value = "/user/mail", method = RequestMethod.GET)
	    //public ModelAndView method() {
	    	//String redirectUrl = "http://svpx-wweb008/fnp14?iduser=994E2D3E0C2138C7C125862D003DC9FF";
	       // return new ModelAndView("redirect:" + redirectUrl);
	    //}

//	    @PostMapping(value = "/redirect")
//	    public RedirectView redirect(@RequestParam Map<String,String> input){
//	  
//	        System.out.println(input);
//	  
//	  
//	        RedirectView redirectView = new RedirectView();
//	        redirectView.setUrl("http://svpx-wweb008/fnp14");
//	  
//	        return redirectView;
//	       
//	    }
	/*    @GetMapping("/createUser")
	    public User createUser() {
	        char[] tempPassword = {'P','a','$','$','w','0','r','d'};
	        User user = UserBuilder.instance()
	            .setEmail("normal.lewis@email.com")
	            .setFirstName("Norman")
	            .setLastName("Lewis")
	            .setPassword(tempPassword)
	            .setActive(true)
	            .buildAndCreate(client);
	        return user;
	    }*/

}
