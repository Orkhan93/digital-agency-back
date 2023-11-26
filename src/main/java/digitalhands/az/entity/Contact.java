package digitalhands.az.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Contact.getAllContacts",
        query = "select new digitalhands.az.wrapper.ContactWrapper(c.name) from Contact c")

@Entity
@Setter
@Getter
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return "Contact{id=%d, name='%s'}".formatted(id, name);
    }

}