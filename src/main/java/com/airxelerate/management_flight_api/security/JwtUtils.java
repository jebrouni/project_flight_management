package com.airxelerate.management_flight_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	private Key getSigningKey() {
		// Convertit la clé secrète en une clé HMAC utilisable
		return Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	public String generateJwtToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(getSigningKey(), SignatureAlgorithm.HS512)
				.compact();
	}

	public String getUsernameFromJwtToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build().parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
			return true;
		} catch (ExpiredJwtException e) {
			System.err.println("JWT expired: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.err.println("JWT unsupported: " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.err.println("JWT malformed: " + e.getMessage());
		} catch (SignatureException e) {
			System.err.println("Invalid JWT signature: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("JWT claims string is empty: " + e.getMessage());
		}
		return false;
	}
}
