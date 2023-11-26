package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Corporate.getAllCorporate",
        query = "select new digitalhands.az.wrapper.CorporateWrapper" +
                "(c.name,c.title,c.content,c.imageData,c.collection.id) from Corporate c")

@Entity
@Getter
@Setter
@Table(name = "corporate")
public class Corporate {

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

    @Column(name = "image")
    private String imageData;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @Override
    public String toString() {
        return "Corporate{id=%d, name='%s', title='%s', content='%s', imageData='%s'}"
                .formatted(id, name, title, content, imageData);
    }

}