package digitalhands.az.request;

import lombok.Data;

@Data
public class UserSignUpRequest {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String status;
    private String userRole;

}