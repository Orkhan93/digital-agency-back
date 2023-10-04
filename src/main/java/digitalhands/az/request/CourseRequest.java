package digitalhands.az.request;

import lombok.Data;

@Data
public class CourseRequest {

    private Long id;
    private String name;
    private String title;
    private String content;
    private byte[] imageData;
    private Long categoryId;

}