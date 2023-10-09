package digitalhands.az.entity;

import
        jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "ContactInformation.getAllContactInformation",
        query = "select new digitalhands.az.wrapper.ContactInformationWrapper" +
                "(c.id,c.name,content,c.address,c.email,c.phone,c.contact.id) from ContactInformation c")

@Entity
@Setter
@Getter
@Table(name = "contact_intormation")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @Override
    public String toString() {
        return "ContactInformation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}