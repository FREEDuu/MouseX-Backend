package EdiaGroup.template.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String jwtSecretString) {
        this.key = Keys.hmacShaKeyFor(jwtSecretString.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract claims", e);
        }
    }

    public String generateToken(String username) {
        long expirationTimeMillis = System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000); // 30 days expiration
        Date expirationDate = new Date(expirationTimeMillis);

        // Create and return the token with all claims in a single builder chain
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim("username", username) // Add custom claim
                .signWith(key)
                .compact();
    }
}