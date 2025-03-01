package EdiaGroup.template.model;

// Test.java

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_datetime")
    private LocalDateTime dateTimeStart;

    @Column(name = "on_sequence")
    private Integer onSequence;

    @Column(name = "type")
    private String type;

    @Column(name = "n_group")
    private Integer nGroup;

    @Column(name = "seconds_delay")
    private Integer secondsDelay;

    @Column(name = "username_datetime_finish")
    private LocalDateTime usernameDatetimeFinish;

    @Column(name = "insert_datetime")
    private LocalDateTime datetimeInsert;

    @Column(name = "n_test")
    private Integer nTest;

    @Column(name = "malus_f5")
    private Integer malusF5;

    @Column(name = "n_error")
    private Integer nError;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private BasicUser basicUser;

    // Constructors, getters, and setters

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public Integer getOnSequence() {
        return onSequence;
    }

    public void setOnSequence(Integer onSequence) {
        this.onSequence = onSequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getnGroup() {
        return nGroup;
    }

    public void setnGroup(Integer nGroup) {
        this.nGroup = nGroup;
    }

    public Integer getSecondsDelay() {
        return secondsDelay;
    }

    public void setSecondsDelay(Integer secondsDelay) {
        this.secondsDelay = secondsDelay;
    }

    public LocalDateTime getUsernameDatetimeFinish() {
        return usernameDatetimeFinish;
    }

    public void setUsernameDatetimeFinish(LocalDateTime usernameDatetimeFinish) {
        this.usernameDatetimeFinish = usernameDatetimeFinish;
    }

    public LocalDateTime getDatetimeInsert() {
        return datetimeInsert;
    }

    public void setDatetimeInsert(LocalDateTime datetimeInsert) {
        this.datetimeInsert = datetimeInsert;
    }

    public Integer getnTest() {
        return nTest;
    }

    public void setnTest(Integer nTest) {
        this.nTest = nTest;
    }

    public Integer getMalusF5() {
        return malusF5;
    }

    public void setMalusF5(Integer malusF5) {
        this.malusF5 = malusF5;
    }

    public Integer getnError() {
        return nError;
    }

    public void setnErrori(Integer nError) {
        this.nError = nError;
    }

    public BasicUser getBasicUser() {
        return basicUser;
    }

    public void setBasicUser(BasicUser basicUser) {
        this.basicUser = basicUser;
    }
}