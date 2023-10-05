package digitalhands.az.repository;

import digitalhands.az.entity.ContactInformation;
import digitalhands.az.wrapper.ContactInformationWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInformationRepository extends JpaRepository<ContactInformation, Long> {

    List<ContactInformationWrapper> getAllContactInformation();

}