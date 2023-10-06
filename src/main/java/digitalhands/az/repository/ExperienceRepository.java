package digitalhands.az.repository;

import digitalhands.az.entity.Experience;
import digitalhands.az.wrapper.ExperienceWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
    List<ExperienceWrapper> getAllExperiences();

}