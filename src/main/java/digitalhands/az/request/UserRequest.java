package digitalhands.az.request;

import digitalhands.az.enums.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;

}