package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
    private String status;
    private String hashedPassword;

    public User(Integer id, String firstName, String lastName, String email, String userType, String status, String hashedPassword) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.hashedPassword = hashedPassword;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", status='" + status + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                '}';
    }
}
