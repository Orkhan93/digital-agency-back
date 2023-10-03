package digitalhands.az.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogPostRequest {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;

}