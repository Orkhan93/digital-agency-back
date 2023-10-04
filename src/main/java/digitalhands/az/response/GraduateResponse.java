package digitalhands.az.response;

import lombok.Data;

@Data
public class GraduateResponse {

    private Long id;
    private String name;
    private String surname;
    private String content;
    private Long experienceId;

}