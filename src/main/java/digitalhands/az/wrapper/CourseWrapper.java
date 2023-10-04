package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWrapper {

    private Long id;
    private String name;
    private String title;
    private String content;
    private byte[] imageData;
    private Long categoryId;

}