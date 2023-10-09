package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GraduateRequest {

    private Long id;
    private String name;
    private String surname;
    private String content;
    private String imageData;
    private Long experienceId;

}