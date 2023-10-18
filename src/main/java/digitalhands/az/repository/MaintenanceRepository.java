package digitalhands.az.repository;

import digitalhands.az.entity.Maintenance;
import digitalhands.az.wrapper.MaintenanceWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<MaintenanceWrapper> getAllMaintenances();

}