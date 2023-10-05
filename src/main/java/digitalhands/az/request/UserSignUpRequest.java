package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpRequest {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String userRole;

}