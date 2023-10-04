package digitalhands.az.request;

import lombok.Data;

@Data
public class GraduateRequest {

    private Long id;
    private String name;
    private String surname;
    private String content;
    private Long experienceId;

}