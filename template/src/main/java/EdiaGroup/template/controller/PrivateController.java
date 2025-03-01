package EdiaGroup.template.controller;

import EdiaGroup.template.model.BasicUser;
import EdiaGroup.template.model.Question;
import EdiaGroup.template.model.TestGroup;
import EdiaGroup.template.repository.BasicUserRepository;
import EdiaGroup.template.repository.TestGroupRepository;
import EdiaGroup.template.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private")
public class PrivateController {

    private final JwtService jwtService;
    private final BasicUserRepository basicUserRepository;
    private final TestGroupRepository testGroupRepository;

    @Autowired
    public PrivateController(JwtService jwtService, BasicUserRepository basicUserRepository, TestGroupRepository testGroupRepository) {
        this.jwtService = jwtService;
        this.basicUserRepository = basicUserRepository;
        this.testGroupRepository = testGroupRepository;
    }


    @GetMapping("/test")
    public ResponseEntity<String> testJwt(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return extractAndProcessToken(authorizationHeader, claims -> "Success for user: " + claims.getSubject());
    }

    @GetMapping("/test-group/get-all")
    public List<TestGroup> getAllTestGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username =  extractAndProcessToken(authorizationHeader, Claims::getSubject).getBody();
        BasicUser user =  basicUserRepository.findByUsername(username);

        return testGroupRepository.findByBasicUser(user);

    }

    @GetMapping("/test-group/create")
    public TestGroup getTestGroup(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String username =  extractAndProcessToken(authorizationHeader, Claims::getSubject).getBody();
        BasicUser user =  basicUserRepository.findByUsername(username);
        TestGroup newTestGroup = new TestGroup(user);
        testGroupRepository.save(newTestGroup);
        return newTestGroup;

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