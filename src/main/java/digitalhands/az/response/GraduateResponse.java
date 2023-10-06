package digitalhands.az.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GraduateResponse {

    private Long id;
    private String name;
    private String surname;
    private String content;
    @JsonIgnore
    private Long experienceId;

}