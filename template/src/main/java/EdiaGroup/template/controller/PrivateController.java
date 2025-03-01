package EdiaGroup.template.controller;

import EdiaGroup.template.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private")
public class PrivateController {

    private final JwtService jwtService;

    @Autowired
    public PrivateController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testJwt(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return extractAndProcessToken(authorizationHeader, claims -> "Success for user: " + claims.getSubject());
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshJwt(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return extractAndProcessToken(authorizationHeader, claims -> jwtService.generateToken(claims.getSubject()));
    }

    private ResponseEntity<String> extractAndProcessToken(String authorizationHeader, TokenProcessor processor) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Authorization header is missing or invalid.");
        }

        String token = authorizationHeader.substring(7);
        try {
            Claims claims = jwtService.extractClaims(token);
            return ResponseEntity.ok(processor.process(claims));
        } catch (JwtException e) {
            return ResponseEntity.status(401).body("Invalid token.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error.");
        }
    }

    @FunctionalInterface
    private interface TokenProcessor {
        String process(Claims claims);
    }
}