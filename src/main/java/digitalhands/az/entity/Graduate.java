package digitalhands.az.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@NamedQuery(name = "Graduate.getAllGraduate",
        query = "select new digitalhands.az.wrapper.GraduateWrapper" +
                "(g.id,g.name,g.surname,g.content,g.experience.id) from Graduate g")

@Entity
@Data
@Table(name = "graduate")
public class Graduate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "content")
    private String content;

    @Lob
    @Column(name = "image_data")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "experience_id", nullable = false)
    @JsonIgnore
    private Experience experience;

}