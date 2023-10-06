package digitalhands.az.service;

import digitalhands.az.entity.Category;
import digitalhands.az.entity.Corporate;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.CategoryNotFoundException;
import digitalhands.az.exception.CorporateNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CorporateMapper;
import digitalhands.az.repository.CategoryRepository;
import digitalhands.az.repository.CorporateRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CorporateRequest;
import digitalhands.az.response.CorporateResponse;
import digitalhands.az.wrapper.CorporateWrapper;
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
public class CorporateService {

    private final CorporateRepository corporateRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final CorporateMapper corporateMapper;

    public ResponseEntity<CorporateResponse> createCorporate(CorporateRequest corporateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Category category = categoryRepository.findById(corporateRequest.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND));
            Corporate corporate = corporateMapper.fromRequestToModel(corporateRequest);
            corporate.setCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(corporateMapper.fromModelToResponse(corporateRepository.save(corporate)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<CorporateResponse> updateCorporate(CorporateRequest corporateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Corporate corporate = corporateRepository.findById(corporateRequest.getId())
                    .orElseThrow(
                            () -> new CorporateNotFoundException(ErrorMessage.CORPORATE_NOT_FOUND));
            if (Objects.nonNull(corporate)) {
                Category category = categoryRepository.findById(corporateRequest.getCategoryId())
                        .orElseThrow(
                                () -> new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND));
                Corporate updatedCorporate = corporateMapper.fromRequestToModel(corporateRequest);
                updatedCorporate.setCategory(category);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(corporateMapper.fromModelToResponse(corporateRepository.save(updatedCorporate)));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<CorporateResponse> getCorporateById(Long corporateId) {
        Corporate corporate = corporateRepository.findById(corporateId)
                .orElseThrow(() -> new CorporateNotFoundException(ErrorMessage.CORPORATE_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(corporateMapper.fromModelToResponse(corporate));
    }

    public ResponseEntity<List<CorporateWrapper>> getAllCorporate() {
        return ResponseEntity.status(HttpStatus.OK).body(corporateRepository.getAllCorporate());
    }

    public void deleteCorporateById(Long userId, Long corporateId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Corporate corporate = corporateRepository.findById(corporateId)
                    .orElseThrow(
                            () -> new CorporateNotFoundException(ErrorMessage.CORPORATE_NOT_FOUND));
            if (Objects.nonNull(corporate)) {
                corporateRepository.deleteById(corporateId);
                log.info("deleteCorporateById {}", corporate);
            }
        }
    }

}