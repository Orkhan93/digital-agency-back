package digitalhands.az.repository;

import digitalhands.az.entity.Corporate;
import digitalhands.az.wrapper.CorporateWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorporateRepository extends JpaRepository<Corporate, Long> {

    List<CorporateWrapper> getAllCorporate();

}