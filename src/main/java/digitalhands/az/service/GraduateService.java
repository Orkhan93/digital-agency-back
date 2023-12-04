package digitalhands.az.service;

import digitalhands.az.entity.Graduate;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.GraduateNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.GraduateMapper;
import digitalhands.az.repository.GraduateRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.GraduateRequest;
import digitalhands.az.response.GraduateResponse;
import digitalhands.az.response.GraduateResponseList;
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

    public GraduateResponse createGraduate(GraduateRequest graduateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Graduate graduate = graduateMapper.fromRequestToModel(graduateRequest);
            return graduateMapper.fromModelToResponse(graduateRepository.save(graduate));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public GraduateResponse updateGraduate(GraduateRequest graduateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Graduate findGraduate = graduateRepository.findById(graduateRequest.getId()).orElseThrow(
                    () -> new GraduateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.GRADUATE_NOT_FOUND));
            if (Objects.isNull(findGraduate)) {
                throw new GraduateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.GRADUATE_NOT_FOUND);
            } else {
                Graduate graduate = graduateMapper.fromRequestToModel(graduateRequest);
                return graduateMapper.fromModelToResponse(graduateRepository.save(graduate));
            }
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public GraduateResponse getGraduateById(Long graduateId) {
        Graduate graduate = graduateRepository.findById(graduateId).orElseThrow(
                () -> new GraduateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.GRADUATE_NOT_FOUND));
        return graduateMapper.fromModelToResponse(graduate);
    }

    public GraduateResponseList getALlGraduates() {
        List<Graduate> all = graduateRepository.findAll();
        GraduateResponseList list = new GraduateResponseList();
        List<GraduateResponse> graduateResponses = graduateMapper.fromModelListToResponseList(all);
        list.setGraduateResponse(graduateResponses);
        return list;
    }

    public void deleteGraduate(Long userId, Long graduateId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            graduateRepository.findById(graduateId).orElseThrow(
                    () -> new GraduateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.GRADUATE_NOT_FOUND));
            graduateRepository.deleteById(graduateId);
        } else
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}