package pl.oli.cantor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.oli.cantor.model.Role;
import pl.oli.cantor.model.UserStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private Integer id;
    private String name;
    private String surname;
    private Role role;
    private String password;
    private UserStatus userStatus;

}
