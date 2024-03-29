package com.openclassrooms.paymybuddy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for Spring Security, enabling and customizing web security for the PayMyBuddy application.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures the SecurityFilterChain to specify security settings like which paths are secured,
     * the form login configuration, remember me functionality, and logout behavior.
     *
     * @param http HttpSecurity used to configure the security filter chain.
     * @return the configured SecurityFilterChain object.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests()
               .requestMatchers("/css/**", "/js/**", "/registration", "/images/**").permitAll()
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .loginPage("/login")
               .permitAll()
               .passwordParameter("password")
               .usernameParameter("email")
               .defaultSuccessUrl("/home", true)
               .and()
               .rememberMe()
               .rememberMeParameter("remember-me")
               .tokenValiditySeconds(86400) // 86400 milliseconds = 24 hours
               .and()
               .logout()
               .logoutUrl("/logout")
               .logoutSuccessUrl("/login?logout=true")
        .permitAll();
        return http.build();
    }

    /**
     * Defines the bean for password encoding using BCrypt hashing algorithm.
     *
     * @return a BCryptPasswordEncoder instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures and returns an AuthenticationManager using the custom user details service
     * and password encoder to support authentication processes.
     *
     * @param http the HttpSecurity object providing context for configuration.
     * @param bCryptPasswordEncoder the BCryptPasswordEncoder for encoding passwords.
     * @return the configured AuthenticationManager instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

}
