package digitalhands.az.entity;

import
        jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "ContactInformation.getAllContactInformation",
        query = "select new digitalhands.az.wrapper.ContactInformationWrapper" +
                "(c.id,content,c.address,c.email,c.phone) from ContactInformation c")

@Entity
@Setter
@Getter
@Table(name = "contact_intormation")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "ContactInformation{id=%d, content='%s', phone='%s', email='%s', address='%s'}"
                .formatted(id, content, phone, email, address);
    }

}