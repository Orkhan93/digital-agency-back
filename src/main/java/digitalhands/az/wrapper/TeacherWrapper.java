package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherWrapper {

    private Long id;
    private String nameAndSurname;
    private String about;
    private byte[] imageData;
    private Long experienceId;

}