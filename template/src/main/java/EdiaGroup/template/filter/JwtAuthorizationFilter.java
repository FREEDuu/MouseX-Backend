package EdiaGroup.template.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final SecretKey secretKey;

    public JwtAuthorizationFilter(String secretKey) {
        // Convert the secret key string into a SecretKey object
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Only filter requests to /private/**
        if (request.getRequestURI().startsWith("/private/")) {
            String token = extractTokenFromHeader(request);

            if (token == null || !isValidToken(token)) {
                sendUnauthorizedResponse(response, "Unauthorized. JWT token is missing or invalid.");
                return;
            }
        }

        // Continue with the next filter if the token is valid
        filterChain.doFilter(request, response);
    }

    // Extract JWT token from the Authorization header
    private String extractTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);  // Return token without "Bearer " prefix
        }
        return null;
    }

    // Check if the token is valid
    private boolean isValidToken(String token) {
        try {
            // Parse and validate the token using the secret key
            Jwts.parser()
                    .verifyWith(secretKey)  // Use verifyWith() for JJWT 0.12.5
                    .build()
                    .parseSignedClaims(token);  // Use parseSignedClaims() for signed JWTs
            return true;
        } catch (Exception e) {
            // Log the exception if needed (e.g., for debugging)
            return false;  // Invalid or expired token
        }
    }

    // Helper method to send an unauthorized response
    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"" + message + "\"}");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 401 Unauthorized
    }
}