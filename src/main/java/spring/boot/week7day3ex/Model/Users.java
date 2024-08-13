package spring.boot.week7day3ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role in('user','admin')")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Name shouldn't be empty")
    @Size(min = 4, message = "Name Length should be more than 4 character")
    @Column(columnDefinition = "varchar(30) not null ")
    private String name;

    @NotEmpty(message = "username shouldn't be empty")
    @Size(min = 4, message = "username Length should be more than 4 character")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotEmpty(message = "password shouldn't be empty ")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @Email
    @NotEmpty(message = "Email shouldn't be empty")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "Role can't be null")
    @Pattern(regexp = "^(user|admin)$", message = "Role must be either 'user' or 'admin'")
    @Column(columnDefinition = "varchar(30) not null")
    private String role;

    @NotNull(message = "Age shouldn't be null")
    @Positive
    @Column(columnDefinition = "int not null ")
    private int age;
}
