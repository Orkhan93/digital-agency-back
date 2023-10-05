package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherRequest {

    private Long id;
    private String nameAndSurname;
    private String about;
    private Long experienceId;

}