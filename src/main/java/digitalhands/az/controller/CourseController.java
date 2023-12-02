package digitalhands.az.controller;

import digitalhands.az.request.CourseRequest;
import digitalhands.az.response.CourseResponse;
import digitalhands.az.response.CourseResponseList;
import digitalhands.az.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest,
                                                       @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CourseResponse> updateCourse(@RequestBody CourseRequest courseRequest,
                                                       @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourse(courseRequest, userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<CourseResponseList> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAllCourses());
    }

    @GetMapping("/get/{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable(name = "courseId") Long courseId) {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourseById(courseId));
    }

    @DeleteMapping("/{userId}/delete/{courseId}")
    public void deleteCourseById(@PathVariable(name = "userId") Long userId,
                                 @PathVariable(name = "courseId") Long courseId) {
        courseService.deleteCourseById(userId, courseId);
    }

}