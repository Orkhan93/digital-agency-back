package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Data;

@NamedQuery(name = "Course.getALlCourses",
        query = "select new digitalhands.az.wrapper.CourseWrapper" +
                "(c.id,c.name,c.title,c.content,c.imageData,c.category.id) from Course c")
@Entity
@Data
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Lob
    @Column(name = "imageData")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}