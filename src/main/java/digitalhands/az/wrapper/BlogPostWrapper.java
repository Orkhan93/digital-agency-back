package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostWrapper {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;

}