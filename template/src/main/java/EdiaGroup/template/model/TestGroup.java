package EdiaGroup.template.model;

// TestGroup.java

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TestGroup")
public class TestGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_datetime")
    private LocalDateTime start;

    @Column(name = "insert_datetime")
    private LocalDateTime insert;

    @Column(name = "on_sequence")
    private Integer onSequence;

    @Column(name = "n_group")
    private Integer nGroup;

    @Column(name = "n_test")
    private Integer nTest;

    @Column(name = "second_delay")
    private Integer secondDelay;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private BasicUser basicUser;

    // Constructors, getters, and setters

    public TestGroup() {
    }
    public TestGroup(BasicUser user) {
        this.setBasicUser(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime insert() {
        return insert;
    }

    public void setInseriment(LocalDateTime insert) {
        this.insert = insert;
    }

    public Integer getOnSequence() {
        return onSequence;
    }

    public void setOnSequence(Integer onSequence) {
        this.onSequence = onSequence;
    }

    public Integer getnGroup() {
        return nGroup;
    }

    public void setnGroup(Integer nGroup) {
        this.nGroup = nGroup;
    }

    public Integer getnTest() {
        return nTest;
    }

    public void setnTest(Integer nTest) {
        this.nTest = nTest;
    }

    public Integer getSecondDelay() {
        return secondDelay;
    }

    public void setSecondDelay(Integer secondDelay) {
        this.secondDelay = secondDelay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BasicUser getBasicUser() {
        return basicUser;
    }

    public void setBasicUser(BasicUser basicUser) {
        this.basicUser = basicUser;
    }
}