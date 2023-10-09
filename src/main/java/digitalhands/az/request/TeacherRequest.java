package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherRequest {

    private Long id;
    private String name;
    private String surname;
    private String about;
    private String imageData;
    private Long experienceId;

}