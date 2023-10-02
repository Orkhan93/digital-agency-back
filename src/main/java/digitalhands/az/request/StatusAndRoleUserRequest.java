package digitalhands.az.request;

import lombok.Data;

@Data
public class StatusAndRoleUserRequest {
    private Long id;
    private String status;
    private String role;
}
