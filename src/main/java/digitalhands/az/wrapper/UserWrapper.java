package digitalhands.az.wrapper;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserWrapper {

    private Long id;
    private String name;
    private String email;
    private String username;
    private String status;

}