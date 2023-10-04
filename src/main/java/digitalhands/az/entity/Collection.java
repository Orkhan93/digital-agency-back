package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@NamedQuery(name = "Collection.getAllCollection",
        query = "select new digitalhands.az.wrapper.CollectionWrapper(c.id,c.name) from Collection c")
@Entity
@Setter
@Getter
@Table(name = "collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL)
    private List<Category> categories;

}