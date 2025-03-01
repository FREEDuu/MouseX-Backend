package EdiaGroup.template.controller;


import EdiaGroup.template.model.Question;
import EdiaGroup.template.repository.BasicUserRepository;
import EdiaGroup.template.repository.QuestionRepository;
import EdiaGroup.template.repository.TestGroupRepository;
import EdiaGroup.template.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
@Tag(name = "Public endpoints", description = "Endpoints for creating question , variants etc.")
public class PublicController {

    private final QuestionRepository questionRepository;
    @Autowired
    public PublicController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostMapping("/question/create")
    public Question createQuestion(@RequestBody Question question) {
        return  questionRepository.save(question);

    }

    @PostMapping("/variant/create")
    public Question createVariant(@RequestBody Question question) {
        return  questionRepository.save(question);

    }
}