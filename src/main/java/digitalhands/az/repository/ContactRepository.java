package digitalhands.az.repository;

import digitalhands.az.entity.Contact;
import digitalhands.az.wrapper.ContactWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<ContactWrapper> getAllContacts();

}