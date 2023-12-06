package com.luv2code.springboot.thymeleafdemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    // add support to JDBC ... No more hardcoded users :-
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configure -> configure
                        .requestMatchers("/").hasRole("EMPLOYEE")
                        .requestMatchers("/leaders/**").hasRole("MANAGER")
                        .requestMatchers("/systems/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(configure ->
                        configure.accessDeniedPage("/access-denied")
                )
                .formLogin( form-> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        //use HTTP basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());


        //disable Cross Site Request Forgery (CSRF)
        // in  general, not required for stateless REST FULL APIs that use POST , PUT, DELETE
        httpSecurity.csrf(csrf -> csrf.disable() );

        return httpSecurity.build();
    }


    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails John = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();
        UserDetails Mary = User.builder().username("mary").password("{noop}test123").roles("EMPLOYEE","MANAGER").build();
        UserDetails Ahmad = User.builder().username("ahmad").password("{noop}test123").roles("EMPLOYEE","MANAGER","ADMIN").build();


        return new InMemoryUserDetailsManager(John,Mary,Ahmad);
    }

     */
}
