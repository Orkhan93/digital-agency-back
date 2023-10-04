package digitalhands.az.repository;

import digitalhands.az.entity.Graduate;
import digitalhands.az.wrapper.GraduateWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GraduateRepository extends JpaRepository<Graduate, Long> {

    List<GraduateWrapper> getAllGraduate();

}