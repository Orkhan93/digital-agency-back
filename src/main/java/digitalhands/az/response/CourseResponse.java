package digitalhands.az.response;

import lombok.Data;

@Data
public class CourseResponse {

    private Long id;
    private String name;
    private String title;
    private String content;
    private byte[] imageData;
    private Long categoryId;

}