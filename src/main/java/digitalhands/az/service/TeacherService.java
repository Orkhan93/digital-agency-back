package digitalhands.az.service;

import digitalhands.az.entity.Experience;
import digitalhands.az.entity.Teacher;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.*;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.TeacherMapper;
import digitalhands.az.repository.ExperienceRepository;
import digitalhands.az.repository.TeacherRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.TeacherResponse;
import digitalhands.az.wrapper.TeacherWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final TeacherMapper teacherMapper;
    private final ExperienceRepository experienceRepository;

    public ResponseEntity<TeacherResponse> createTeacher(TeacherRequest teacherRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Experience experience = experienceRepository.findById(teacherRequest.getExperienceId()).orElseThrow(
                    () -> new ExperienceNotFoundException(ErrorMessage.EXPERIENCE_NOT_FOUND));
            Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
            teacher.setExperienceTeacher(experience);//TODO set eliyende experience id null verir
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(teacherMapper.fromModelToResponse(teacherRepository.save(teacher)));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<TeacherResponse> updateTeacher(TeacherRequest teacherRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Teacher findTeacher = teacherRepository
                    .findById(teacherRequest.getId()).orElseThrow(
                            () -> new TeacherNotFoundException(ErrorMessage.TEACHER_NOT_FOUND));
            if (Objects.nonNull(findTeacher)) {
                Experience experience = experienceRepository.findById(teacherRequest.getExperienceId()).orElseThrow(
                        () -> new ExperienceNotFoundException(ErrorMessage.EXPERIENCE_NOT_FOUND));
                Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
                teacher.setExperienceTeacher(experience);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(teacherMapper.fromModelToResponse(teacherRepository.save(teacher)));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<List<TeacherWrapper>> getAllTeachers() {
        return ResponseEntity.status(HttpStatus.OK).body
                (teacherRepository.getAllTeachers());
    }

    public ResponseEntity<TeacherResponse> getTeacherById(Long teacherID) {
        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(
                () -> new BlogPostNotFoundException(ErrorMessage.TEACHER_NOT_FOUND));
        if (Objects.nonNull(teacher)) {
            return ResponseEntity.status(HttpStatus.OK).body(teacherMapper.fromModelToResponse(teacher));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public void deleteAllTeachers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            teacherRepository.deleteAll();
            log.info("deleteAllTeachers {} ", user);
        }
    }

    public void deleteTeacher(Long userId, Long teacherId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Teacher teacher = teacherRepository
                    .findById(teacherId).orElseThrow(
                            () -> new TeacherNotFoundException(ErrorMessage.TEACHER_NOT_FOUND));
            teacherRepository.deleteById(teacherId);
            log.info("deleteById {}", teacher);
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

}