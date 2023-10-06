package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseWrapper {

    private String name;
    private String title;
    private String content;
    private byte[] imageData;

}