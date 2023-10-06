package digitalhands.az.service;

import digitalhands.az.entity.*;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.*;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CourseMapper;
import digitalhands.az.repository.CategoryRepository;
import digitalhands.az.repository.CourseRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CourseRequest;
import digitalhands.az.response.CourseResponse;
import digitalhands.az.wrapper.CourseWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final CourseMapper courseMapper;
    private final UserRepository userRepository;

    public ResponseEntity<CourseResponse> createCourse(CourseRequest courseRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Category category = categoryRepository.findById(courseRequest.getCategoryId()).orElseThrow(
                    () -> new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND));
            Course course = courseMapper.fromRequestToModel(courseRequest);
            course.setCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(courseMapper.fromModelToResponse
                            (courseRepository.save(course)));
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    public ResponseEntity<CourseResponse> updateCourse(CourseRequest courseRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Course course = courseRepository.findById(courseRequest.getId())
                    .orElseThrow(() -> new CourseNotFoundException(ErrorMessage.COURSE_NOT_FOUND));
            if (Objects.nonNull(course)) {
                Category category = categoryRepository.findById(courseRequest.getCategoryId())
                        .orElseThrow(() -> new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND));
                Course updateCourse = courseMapper.fromRequestToModel(courseRequest);
                updateCourse.setCategory(category);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(courseMapper.fromModelToResponse
                                (courseRepository.save(updateCourse)));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<CourseResponse> getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(ErrorMessage.COURSE_NOT_FOUND));
        if (Objects.nonNull(course)) {
            return ResponseEntity.status(HttpStatus.OK).body(courseMapper.fromModelToResponse(course));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<CourseWrapper>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseRepository.getAllCourses());
    }

    public void deleteCourseById(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException(ErrorMessage.COURSE_NOT_FOUND));
            if (Objects.nonNull(course)) {
                courseRepository.deleteById(courseId);
                log.info("deleteCourseById {}", course);
            }
        }
    }

}