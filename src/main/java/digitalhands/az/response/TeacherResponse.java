package digitalhands.az.response;

import lombok.Data;

@Data
public class TeacherResponse {

    private Long id;
    private String nameAndSurname;
    private String about;
    private Long experienceId;

}