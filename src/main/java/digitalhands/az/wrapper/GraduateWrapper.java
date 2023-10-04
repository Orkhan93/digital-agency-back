package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraduateWrapper {

    private Long id;
    private String name;
    private String surname;
    private String content;
    private Long experienceId;

}