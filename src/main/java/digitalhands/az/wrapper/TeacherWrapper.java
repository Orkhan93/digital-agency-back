package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherWrapper {

    private Long id;
    private String nameAndSurname;
    private String about;
    private byte[] imageData;
    private Long experienceId;

    public TeacherWrapper(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

}