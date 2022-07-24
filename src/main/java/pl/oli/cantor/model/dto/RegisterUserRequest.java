package pl.oli.cantor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oli.cantor.model.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserRequest {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String pesel;
    private Role role;
}
