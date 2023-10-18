package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Maintenance.getAllMaintenances",
        query = "select new digitalhands.az.wrapper.MaintenanceWrapper(m.id,m.title,m.content) from Maintenance m")

@Entity
@Getter
@Setter
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "imageData")
    private String imageData;

    @Column(name = "content")
    private String content;

    @Override
    public String toString() {
        return "Maintenance{id=%d, title='%s', imageData='%s', content='%s'}"
                .formatted(id, title, imageData, content);
    }

}