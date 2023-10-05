package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@NamedQuery(name = "Category.getAllCategories",
        query = "select new digitalhands.az.wrapper.CategoryWrapper(c.id,collection.name,c.collection.id) from Category  c")
@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Course> courses;

}