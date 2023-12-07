
package digitalhands.az.controller;

import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.TeacherResponse;
import digitalhands.az.response.TeacherResponseList;
import digitalhands.az.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/teacher")
@RequiredArgsConstructor
@RestController
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherRequest teacherRequest,
                                                         @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long userId,
                                           @RequestBody TeacherRequest teacherRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.updateTeacher(teacherRequest, userId));

    }

    @GetMapping("/getAll")
    public ResponseEntity<TeacherResponseList> getAllTeachers() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeachers());

    }

    @GetMapping("/get/{teacherId}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable(name = "teacherId") Long teacherId) {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacherById(teacherId));
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