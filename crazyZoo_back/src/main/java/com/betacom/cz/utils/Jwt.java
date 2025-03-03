package com.betacom.cz.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Jwt {

	private final Key key;

	public Jwt(@Value("${jwt.secret}") String secret) {
		if (secret == null || secret.trim().isEmpty()) {
			throw new IllegalArgumentException("Secret key is missing!");
		}
		this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
	}

	public String generateToken(String username, String ruolo) {
		return Jwts.builder()
				.setSubject(username)
				.claim("ruolo", ruolo)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}

}
