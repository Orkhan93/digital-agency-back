package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Corporate.getAllCorporate",
        query = "select new digitalhands.az.wrapper.CorporateWrapper" +
                "(c.id,c.name,c.title,c.content,c.imageData,c.category.id) from Corporate c")

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
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}