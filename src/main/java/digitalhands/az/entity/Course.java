package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Course.getAllCourses",
        query = "select new digitalhands.az.wrapper.CourseWrapper" +
                "(c.name,c.title,c.content,c.imageData) from Course c")
@Entity
@Setter
@Getter
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

    @Column(name = "imageData")
    private String imageData;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Override
    public String toString() {
        return "Course{id=%d, name='%s', title='%s', content='%s', imageData='%s'}"
                .formatted(id, name, title, content, imageData);
    }
}