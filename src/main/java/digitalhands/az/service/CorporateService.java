package digitalhands.az.service;

import digitalhands.az.entity.Collection;
import digitalhands.az.entity.Corporate;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.*;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.CorporateMapper;
import digitalhands.az.repository.CollectionRepository;
import digitalhands.az.repository.CorporateRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.CorporateRequest;
import digitalhands.az.response.CorporateResponse;
import digitalhands.az.response.CorporateResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorporateService {

    private final CorporateRepository corporateRepository;
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CorporateMapper corporateMapper;

    public CorporateResponse createCorporate(CorporateRequest corporateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Collection collection = collectionRepository.findById(corporateRequest.getCollectionId())
                    .orElseThrow(() -> new CollectionNotFoundException(HttpStatus.NOT_FOUND.name(),
                            ErrorMessage.COLLECTION_NOT_FOUND));
            Corporate corporate = corporateMapper.fromRequestToModel(corporateRequest);
            corporate.setCollection(collection);
            return corporateMapper.fromModelToResponse(corporateRepository.save(corporate));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CorporateResponse updateCorporate(CorporateRequest corporateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Corporate corporate = corporateRepository.findById(corporateRequest.getId())
                    .orElseThrow(
                            () -> new CorporateNotFoundException(HttpStatus.NOT_FOUND.name(),
                                    ErrorMessage.CORPORATE_NOT_FOUND));
            if (Objects.nonNull(corporate)) {
                Collection collection = collectionRepository.findById(corporateRequest.getCollectionId())
                        .orElseThrow(
                                () -> new CategoryNotFoundException(HttpStatus.NOT_FOUND.name(),
                                        ErrorMessage.CATEGORY_NOT_FOUND));
                Corporate updatedCorporate = corporateMapper.fromRequestToModel(corporateRequest);
                updatedCorporate.setCollection(collection);
                return corporateMapper.fromModelToResponse(corporateRepository.save(updatedCorporate));
            }
            throw new CorporateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.CORPORATE_NOT_FOUND);
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public CorporateResponse getCorporateById(Long corporateId) {
        Corporate corporate = corporateRepository.findById(corporateId)
                .orElseThrow(() -> new CorporateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.CORPORATE_NOT_FOUND));
        return corporateMapper.fromModelToResponse(corporate);
    }

    public CorporateResponseList getAllCorporate() {
        List<Corporate> all = corporateRepository.findAll();
        CorporateResponseList list = new CorporateResponseList();
        List<CorporateResponse> corporateResponses = corporateMapper.fromModelListToResponseList(all);
        list.setCorporateResponses(corporateResponses);
        return list;
    }

    public void deleteCorporateById(Long userId, Long corporateId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Corporate corporate = corporateRepository.findById(corporateId)
                    .orElseThrow(
                            () -> new CorporateNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.CORPORATE_NOT_FOUND));
            if (Objects.nonNull(corporate)) {
                corporateRepository.deleteById(corporateId);
                log.info("deleteCorporateById {}", corporate);
            }
        }
    }

}