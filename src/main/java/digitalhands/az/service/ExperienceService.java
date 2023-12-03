package digitalhands.az.service;

import digitalhands.az.entity.Experience;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ExperienceNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.ExperienceMapper;
import digitalhands.az.repository.ExperienceRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ExperienceRequest;
import digitalhands.az.response.ExperienceResponse;
import digitalhands.az.response.ExperienceResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final UserRepository userRepository;
    private final ExperienceMapper experienceMapper;


    public ExperienceResponse createExperience(ExperienceRequest experienceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Experience savedExperience = experienceRepository.save(experienceMapper.fromRequestToModel(experienceRequest));
            return experienceMapper.fromModelToResponse(savedExperience);
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public ExperienceResponse updateExperience(ExperienceRequest experienceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        Experience experience = experienceRepository.findById(experienceRequest.getId()).orElseThrow(
                () -> new ExperienceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.EXPERIENCE_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            if (Objects.isNull(experience)) {
                throw new ExperienceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.EXPERIENCE_NOT_FOUND);
            } else {
                return experienceMapper.fromModelToResponse
                        (experienceRepository.save(experienceMapper.fromRequestToModel(experienceRequest)));
            }
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public ExperienceResponse getExperienceById(Long experienceId) {
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(
                () -> new ExperienceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.EXPERIENCE_NOT_FOUND));
        return experienceMapper.fromModelToResponse(experience);
    }

    public ExperienceResponseList getAllExperiences() {
        List<Experience> all = experienceRepository.findAll();
        ExperienceResponseList list = new ExperienceResponseList();
        List<ExperienceResponse> experienceResponses = experienceMapper.fromModelListToResponseList(all);
        list.setExperienceResponses(experienceResponses);
        return list;
    }

    public void deleteExperience(Long userId, Long experienceId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        Experience experience = experienceRepository.findById(experienceId).orElseThrow(
                () -> new ExperienceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.EXPERIENCE_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            if (Objects.nonNull(experience)) {
                experienceRepository.deleteById(experienceId);
                log.info("deleteExperience {}", experience);
            }
        }
    }

}