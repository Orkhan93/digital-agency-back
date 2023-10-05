package digitalhands.az.repository;

import digitalhands.az.entity.Course;
import digitalhands.az.wrapper.CourseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<CourseWrapper> getAllCourses();

}