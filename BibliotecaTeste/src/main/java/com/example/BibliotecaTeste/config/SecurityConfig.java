package com.example.BibliotecaTeste.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeHttpRequests(t -> t
				// liberando o get para todos tanto como
				.requestMatchers(HttpMethod.GET, "/livros/**").permitAll().requestMatchers(HttpMethod.GET, "/autor/**")
				.permitAll()
				// liberando livros post,put e delete so para admin
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/livros/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/livros/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/livros/**").hasRole("ADMIN")
				// liberando post,put,delete para autores
				.requestMatchers(HttpMethod.POST, "/autor/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/autor/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/autor/**").hasRole("ADMIN")
				//aqui eu declaro que qualquer outra rota vai ser preciso esta autenticada
				.anyRequest().authenticated()
				
				)
		.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		//aqui e uma cripitografia de senha de forca 5
		return new BCryptPasswordEncoder(5);
	}

}
