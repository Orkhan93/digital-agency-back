package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@NamedQuery(name = "Experience.getAllExperiences",
        query = "select new digitalhands.az.wrapper.ExperienceWrapper(e.name) from Experience e")

@Entity
@Setter
@Getter
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "experience",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<BlogPost> blogPostList;

    @OneToMany(mappedBy = "experience",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Graduate> graduateList;

    @OneToMany(mappedBy = "experience",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Teacher> teacherList;

    @Override
    public String toString() {
        return "Experience{id=%d, name='%s'}".formatted(id, name);
    }

}