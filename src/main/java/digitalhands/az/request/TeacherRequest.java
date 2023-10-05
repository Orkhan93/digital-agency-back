package digitalhands.az.request;

import lombok.Data;

@Data
public class TeacherRequest {

    private Long id;
    private String nameAndSurname;
    private String about;
    private Long experienceId;

}