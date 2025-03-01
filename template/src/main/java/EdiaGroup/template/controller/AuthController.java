package EdiaGroup.template.controller;

import EdiaGroup.template.model.BasicUser;
import EdiaGroup.template.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateToken(@Valid @RequestBody BasicUser user) {
        return authService.generateToken(user);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody BasicUser user) {
        return authService.registerUser(user);
    }
}