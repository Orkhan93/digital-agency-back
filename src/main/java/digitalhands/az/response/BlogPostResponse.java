package digitalhands.az.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BlogPostResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private Long experienceId;

}