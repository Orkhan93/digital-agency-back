package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

    private Long id;
    private String nameAndSurname;
    private String about;
    private Long experienceId;

}