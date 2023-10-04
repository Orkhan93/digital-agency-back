package digitalhands.az.repository;

import digitalhands.az.entity.Collection;
import digitalhands.az.wrapper.CollectionWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    List<CollectionWrapper> getAllCollection();

}