package digitalhands.az.repository;

import digitalhands.az.entity.Teacher;
import digitalhands.az.wrapper.TeacherWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<TeacherWrapper> getAllTeachers();

}