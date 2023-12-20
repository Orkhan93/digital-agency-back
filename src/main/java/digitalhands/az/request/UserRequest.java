package digitalhands.az.request;

import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.errors.constraint.validation.Name;
import digitalhands.az.exception.errors.constraint.validation.Password;
import digitalhands.az.exception.errors.constraint.validation.Username;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    private Long id;

    @Name
    private String name;

    @Username
    private String username;

    @Email
    private String email;

    @Password
    private String password;
    private UserRole userRole;

}