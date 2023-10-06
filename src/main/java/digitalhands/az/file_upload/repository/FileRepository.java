package digitalhands.az.file_upload.repository;

import digitalhands.az.file_upload.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}