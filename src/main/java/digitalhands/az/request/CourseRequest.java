package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseRequest {

    private Long id;
    private String name;
    private String title;
    private String content;
    private Long categoryId;

}