/**
 * 
 */
package com.black_queen.auth_service.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final JwtProperties jwtProperties;

    private String secretKey;

    public JwtTokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }
    
    public String getJwt(String userId) {
    	return Jwts.builder()
				.setSubject(userId)
				.setIssuer("")
				.setExpiration(this.calculateExpirationDate())
				.signWith(SignatureAlgorithm.HS256, this.secretKey)
				.compact();
	}
    
    private Date calculateExpirationDate() {
		Date now = new Date();
		return new Date(now.getTime() + Integer.parseInt(jwtProperties.getTokenValidityMilliSeconds()));
	}

    public String getUserId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Cookie");

        return (!Objects.isNull(bearerToken) && bearerToken.startsWith("JWT")) ?
                bearerToken.substring(3, bearerToken.length()) : null;
    }

    public boolean validateToken(String token) throws Exception {

        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            return (!claims.getBody().getExpiration().before(new Date()));
        } catch (JwtException | IllegalArgumentException e) {
            LOGGER.error("Expired or invalid JWT token");
            throw new Exception();
        }
    }
}
