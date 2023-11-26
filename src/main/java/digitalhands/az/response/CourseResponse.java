package digitalhands.az.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseResponse {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String imageData;
    @JsonIgnore
    private Long categoryId;

}