
package digitalhands.az.controller;

import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.TeacherResponse;
import digitalhands.az.service.TeacherService;
import digitalhands.az.wrapper.TeacherWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teacher")
@RequiredArgsConstructor
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<TeacherResponse> createBlog(@RequestBody TeacherRequest teacherRequest,
                                                      @PathVariable Long userId) {
        return teacherService.createTeacher(teacherRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long userId,
                                           @RequestBody TeacherRequest teacherRequest) {
        return teacherService.updateTeacher(teacherRequest, userId);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TeacherWrapper>> getAllTeachers() {
        return teacherService.getAllTeachers();

    }

    @GetMapping("/get/{teacherId}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable(name = "teacherId") Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @DeleteMapping("/deleteAll/{userId}")
    public void deleteAllTeachers(@PathVariable Long userId) {
        teacherService.deleteAllTeachers(userId);
    }
    @DeleteMapping("/{userId}/delete/{teacherId}")
    public void deleteCategory(@PathVariable Long userId,
                               @PathVariable Long teacherId) {
        teacherService.deleteTeacher(userId, teacherId);
    }

}