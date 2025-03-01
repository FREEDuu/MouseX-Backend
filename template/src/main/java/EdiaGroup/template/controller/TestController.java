package EdiaGroup.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Greetings", description = "Endpoints for greetings")
public class TestController {

    @GetMapping("auth/hello")
    @Operation(summary = "Get a greeting", responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public String hello() {
        return "Hello, Spring Boot!";
    }
}