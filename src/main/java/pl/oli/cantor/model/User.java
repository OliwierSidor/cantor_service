package pl.oli.cantor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import pl.oli.cantor.model.dto.RegisterUserRequest;
import pl.oli.cantor.model.dto.UserDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

import static pl.oli.cantor.model.Role.USER;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;
    private String login;
    private String password;

    @CreationTimestamp
    private LocalDateTime registrationDate;
    private String pesel;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    private UserStatus userStatus;

    public static User from(RegisterUserRequest request, UserStatus userStatus) {
        return new User(null, request.getName(), request.getSurname(), request.getLogin(), request.getPassword(), null, request.getPesel(), USER, userStatus);
    }

    public UserDTO mapToDTO() {
        return new UserDTO(id, name, surname, role);
    }
}
