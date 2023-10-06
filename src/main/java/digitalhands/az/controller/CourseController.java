package digitalhands.az.controller;

import digitalhands.az.request.CourseRequest;
import digitalhands.az.response.CourseResponse;
import digitalhands.az.service.CourseService;
import digitalhands.az.wrapper.CourseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest,
                                                       @PathVariable(name = "userId") Long userId) {
        return courseService.createCourse(courseRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseRequest courseRequest,
                                                       @PathVariable(name = "userId") Long userId) {
        return courseService.updateCourse(courseRequest, userId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseWrapper>> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable(name = "courseId") Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @DeleteMapping("/{userId}/delete/{courseId}")
    public void deleteCourseById(@PathVariable(name = "userId") Long userId,
                                 @PathVariable(name = "courseId") Long courseId) {
        courseService.deleteCourseById(userId, courseId);
    }

}