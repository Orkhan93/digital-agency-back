package digitalhands.az.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private String email;

}