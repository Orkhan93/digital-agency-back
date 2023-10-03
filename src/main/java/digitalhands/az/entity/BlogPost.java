package digitalhands.az.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@NamedQuery(name = "BlogPost.getAllBlogPosts", query = "select new digitalhands.az.wrapper.BlogPostWrapper(b.id,b.title,b.content,b.creationDate) from BlogPost b")

@Entity
@Data
@Table(name = "blog_post")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationDate")
    @JsonFormat(pattern = "dd-mm-yyyy'T'HH:mm")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    @JsonIgnore
    private Experience experience;

}