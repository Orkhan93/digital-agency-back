package digitalhands.az.service;

import digitalhands.az.entity.Experience;
import digitalhands.az.entity.Graduate;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ExperienceNotFoundException;
import digitalhands.az.exception.GraduateNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.GraduateMapper;
import digitalhands.az.repository.ExperienceRepository;
import digitalhands.az.repository.GraduateRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.GraduateRequest;
import digitalhands.az.response.GraduateResponse;
import digitalhands.az.wrapper.GraduateWrapper;
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
public class GraduateService {

    private final GraduateRepository graduateRepository;
    private final UserRepository userRepository;
    private final GraduateMapper graduateMapper;
    private final ExperienceRepository experienceRepository;

    public ResponseEntity<GraduateResponse> createGraduate(GraduateRequest graduateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Experience experience = experienceRepository.findById(graduateRequest.getExperienceId()).orElseThrow(
                    () -> new ExperienceNotFoundException(ErrorMessage.EXPERIENCE_NOT_FOUND));
            Graduate graduate = graduateMapper.fromRequestToModel(graduateRequest);
            graduate.setExperience(experience);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(graduateMapper.fromModelToResponse(graduateRepository.save(graduate)));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<GraduateResponse> updateGraduate(GraduateRequest graduateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Graduate findGraduate = graduateRepository.findById(graduateRequest.getId()).orElseThrow(
                    () -> new GraduateNotFoundException(ErrorMessage.GRADUATE_NOT_FOUND));
            if (Objects.nonNull(findGraduate)) {
                Experience experience = experienceRepository.findById(graduateRequest.getExperienceId()).orElseThrow(
                        () -> new ExperienceNotFoundException(ErrorMessage.GRADUATE_NOT_FOUND));
                Graduate graduate = graduateMapper.fromRequestToModel(graduateRequest);
                graduate.setExperience(experience);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(graduateMapper.fromModelToResponse(graduateRepository.save(graduate)));
            } else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<GraduateResponse> getGraduateById(Long graduateId) {
        Graduate graduate = graduateRepository.findById(graduateId).orElseThrow(
                () -> new GraduateNotFoundException(ErrorMessage.GRADUATE_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(graduateMapper.fromModelToResponse(graduate));
    }

    public ResponseEntity<List<GraduateWrapper>> getALlGraduates() {
        return ResponseEntity.status(HttpStatus.OK).body(graduateRepository.getAllGraduate());
    }

    public void deleteGraduate(Long userId, Long graduateId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            graduateRepository.findById(graduateId).orElseThrow(
                    () -> new GraduateNotFoundException(ErrorMessage.GRADUATE_NOT_FOUND));
            graduateRepository.deleteById(graduateId);
        } else
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}