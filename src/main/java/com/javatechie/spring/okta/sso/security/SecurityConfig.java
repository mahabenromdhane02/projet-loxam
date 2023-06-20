package com.javatechie.spring.okta.sso.security;

import com.javatechie.spring.okta.sso.controller.ApplicationController;
import com.okta.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
@Order
@Configuration 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    Client oktaClient;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override  
    public void configure(HttpSecurity http) throws Exception {
        http
       .authorizeRequests()
                .antMatchers("/**").permitAll()
    /*   .antMatchers("/login").permitAll()
       .antMatchers("/listusers").permitAll()*/
       .anyRequest().authenticated()
       .and()
        .httpBasic();
        http.addFilterBefore(new OktaSecurityFilter(getRequestMatcher(), oktaClient, jdbcTemplate), UsernamePasswordAuthenticationFilter.class);
    }  
      
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.inMemoryAuthentication() .withUser("user")
	 * .password("{noop}pass") // Spring Security 5 requires specifying the password
	 * storage format .roles("USER"); }
	 */
    
    @Bean
    public RequestMatcher getRequestMatcher() {
    	return new OrRequestMatcher(new AntPathRequestMatcher("/**"));
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
