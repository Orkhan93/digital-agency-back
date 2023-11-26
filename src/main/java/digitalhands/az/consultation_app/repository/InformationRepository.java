package digitalhands.az.consultation_app.repository;

import digitalhands.az.consultation_app.model.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository
        extends JpaRepository<Information, Long> {
}
