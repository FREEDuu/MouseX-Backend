package EdiaGroup.template.service;

import EdiaGroup.template.model.BasicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final BasicUserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthService(BasicUserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Map<String, String>> generateToken(BasicUser user) {
        Optional<BasicUser> foundUser = userService.findUserByUsername(user.getUsername());

        if (foundUser.isPresent() && userService.validatePassword(user.getPassword(), foundUser.get().getPassword())) {
            String token = jwtService.generateToken(user.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    public ResponseEntity<String> registerUser(BasicUser user) {
        return userService.registerUser(user);
    }
}