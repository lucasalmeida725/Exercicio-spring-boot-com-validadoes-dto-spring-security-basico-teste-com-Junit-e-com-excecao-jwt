package com.example.BibliotecaTeste.config;

import java.security.Key;
import java.util.Date;
import java.util.Set;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long EXPIRATION = 1000 * 60 * 60; // 1 hora
	private static final String SECRET = "MinhaChaveSuperSecreta1234567890";
	public static String generateToken(String email, Set<String> roles) {
		return Jwts.builder().setSubject(email).claim("roles", roles)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(key).compact();
	}

	public static String getEmail(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}

	@SuppressWarnings("unchecked")
	public static Set<String> getRoles(String token) {
		return (Set<String>) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
				.get("roles");
	}
}
