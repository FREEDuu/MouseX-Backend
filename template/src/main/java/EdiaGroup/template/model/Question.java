package EdiaGroup.template.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "body", columnDefinition = "TEXT") // or VARCHAR(255) if appropriate
    private String body;

    @Column(name = "datetime_insert")
    private LocalDateTime datetimeInsert;

    @Column(name = "type")
    private String type;

    @Column(name = "n_page")
    private Integer nPage;

    @Column(name = "is_active")
    private Boolean isActive;

    // Constructors (default and parameterized)
    public Question() {
    }

    public Question(String body, LocalDateTime datetimeInsert, String type, Integer nPage, Boolean isActive) {
        this.body = body;
        this.datetimeInsert = datetimeInsert;
        this.type = type;
        this.nPage = nPage;
        this.isActive = isActive;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getDatetimeInsert() {
        return datetimeInsert;
    }

    public void setDatetimeInsert(LocalDateTime datetimeInsert) {
        this.datetimeInsert = datetimeInsert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getnPage() {
        return nPage;
    }

    public void setnPage(Integer nPage) {
        this.nPage = nPage;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}