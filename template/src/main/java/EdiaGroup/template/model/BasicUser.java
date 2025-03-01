package EdiaGroup.template.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "basic_user") // Optional: Specify the table name
public class BasicUser {

    @Id
    @NotBlank(message = "Username is required")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank(message = "password is required")
    @Column(name = "password", nullable = false)
    private String password;

    // Default constructor (required by JPA)
    public BasicUser() {
    }

    // Constructor with parameters
    public BasicUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "BasicUser{" +
                "username='" + username + '\'' +
                ", password='" + (password != null ? "[PROTECTED]" : null) + '\'' + // Avoid printing passwords in logs
                '}';
    }
}