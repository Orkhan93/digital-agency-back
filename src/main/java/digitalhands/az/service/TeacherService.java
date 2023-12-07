package digitalhands.az.service;

import digitalhands.az.entity.Teacher;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.BlogPostNotFoundException;
import digitalhands.az.exception.TeacherNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.TeacherMapper;
import digitalhands.az.repository.TeacherRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.TeacherResponse;
import digitalhands.az.response.TeacherResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    public TeacherResponse createTeacher(TeacherRequest teacherRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
            return teacherMapper.fromModelToResponse(teacherRepository.save(teacher));
        } else
            throw new TeacherNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND);
    }

    public TeacherResponse updateTeacher(TeacherRequest teacherRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Teacher findTeacher = teacherRepository
                    .findById(teacherRequest.getId()).orElseThrow(
                            () -> new TeacherNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND));
            if (Objects.nonNull(findTeacher)) {
                Teacher teacher = teacherMapper.fromRequestToModel(teacherRequest);
                return teacherMapper.fromModelToResponse(teacherRepository.save(teacher));
            }
            throw new TeacherNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND);
        }
        throw new UserNotFoundException(HttpStatus.UNAUTHORIZED.name());
    }

    public TeacherResponseList getAllTeachers() {
        List<Teacher> all = teacherRepository.findAll();
        TeacherResponseList list = new TeacherResponseList();
        List<TeacherResponse> teacherResponses = teacherMapper.fromModelListToResponseList(all);
        list.setTeacherResponses(teacherResponses);
        return list;
    }

    public TeacherResponse getTeacherById(Long teacherID) {
        Teacher teacher = teacherRepository.findById(teacherID).orElseThrow(
                () -> new TeacherNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND));
            return teacherMapper.fromModelToResponse(teacher);
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
                            () -> new TeacherNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.TEACHER_NOT_FOUND));
            teacherRepository.deleteById(teacherId);
            log.info("deleteById {}", teacher);
        }
    }

}