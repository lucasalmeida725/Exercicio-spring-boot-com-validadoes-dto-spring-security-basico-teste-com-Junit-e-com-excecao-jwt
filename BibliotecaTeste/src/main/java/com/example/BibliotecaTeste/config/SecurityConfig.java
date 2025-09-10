package com.example.BibliotecaTeste.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests(t -> t
				// liberando o get para todos tanto como
				.requestMatchers(HttpMethod.GET, "/livros/**").permitAll().requestMatchers(HttpMethod.GET, "/autor/**")
				.permitAll()
				// liberando livros post,put e delete so para admin

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
		.httpBasic();
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		//aqui e uma cripitografia de senha de forca 5
		return new BCryptPasswordEncoder(5);
	}
	@Bean
	public UserDetailsService userDetails(PasswordEncoder encoder) {
		UserDetails user=User.builder().username("usuario")//nome do usario
				.password(encoder.encode("123"))//senha codificada
				.roles("USER")//roles ao usuario
				.build();
		
		UserDetails admin=User.builder()
				.username("Adm")
				.password(encoder.encode("lucas123"))
				.roles("ADMIN")
				.build();
		return  new InMemoryUserDetailsManager(user,admin);
	}
}
