package digitalhands.az.request;

import digitalhands.az.exception.errors.constraint.validation.Password;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginRequest {

    @Email
    String email;

    @Password
    String password;

}