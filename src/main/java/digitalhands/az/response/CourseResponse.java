package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseResponse {

    private Long id;
    private String name;
    private String title;
    private String content;
    private Long categoryId;

}