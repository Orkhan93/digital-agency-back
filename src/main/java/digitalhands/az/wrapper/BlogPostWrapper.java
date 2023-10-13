package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostWrapper {

    private String title;
    private String content;
    private LocalDateTime creationDate;
    private String imageOfBlogPost;

}