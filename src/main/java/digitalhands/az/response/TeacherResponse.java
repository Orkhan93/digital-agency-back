package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherResponse {

    private Long id;
    private String name;
    private String surname;
    private String about;
    private String imageData;

}