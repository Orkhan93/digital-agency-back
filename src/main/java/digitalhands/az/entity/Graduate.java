package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Graduate.getAllGraduate",
        query = "select new digitalhands.az.wrapper.GraduateWrapper" +
                "(g.name,g.surname,g.content,g.imageData) from Graduate g")

@Entity
@Setter
@Getter
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

    @Column(name = "image_data")
    private String imageData;


    @Override
    public String toString() {
        return "Graduate{id=%d, name='%s', surname='%s', content='%s', imageData='%s'}"
                .formatted(id, name, surname, content, imageData);
    }

}