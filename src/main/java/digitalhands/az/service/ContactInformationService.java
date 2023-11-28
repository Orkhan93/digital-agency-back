package digitalhands.az.service;

import digitalhands.az.entity.ContactInformation;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ContactInformationNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.ContactInformationMapper;
import digitalhands.az.repository.ContactInformationRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import digitalhands.az.response.ContactInformationResponseList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    private final UserRepository userRepository;
    private final ContactInformationMapper contactInformationMapper;

    public ContactInformationResponse createContactInformation
            (ContactInformationRequest contactInformationRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            return contactInformationMapper.fromModelToResponse(contactInformationRepository
                    .save(contactInformationMapper.fromRequestToModel(contactInformationRequest)));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public ContactInformationResponse updateContactInformation
            (ContactInformationRequest contactInformationRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            ContactInformation contactInformation = contactInformationRepository.findById(contactInformationRequest.getId())
                    .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
            if (Objects.isNull(contactInformation)) {
                throw new ContactInformationNotFoundException(HttpStatus.NOT_FOUND.name());
            } else {
                ContactInformation updated = contactInformationMapper.fromRequestToModel(contactInformationRequest);
                return contactInformationMapper.fromModelToResponse(contactInformationRepository.save(updated));
            }
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public ContactInformationResponse getContactInformationById(Long contactInformationId) {
        ContactInformation contactInformation = contactInformationRepository.findById(contactInformationId)
                .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
        return contactInformationMapper.fromModelToResponse(contactInformation);
    }

    public ContactInformationResponseList getAllContactInformation() {
        List<ContactInformation> all = contactInformationRepository.findAll();
        ContactInformationResponseList list = new ContactInformationResponseList();
        List<ContactInformationResponse> responses = contactInformationMapper.fromModelListToResponseList(all);
        list.setContactInformationResponses(responses);
        return list;
    }

    public void deleteContactInformationById(Long userId, Long contactInformationId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            ContactInformation contactInformation = contactInformationRepository.findById(contactInformationId)
                    .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
            if (Objects.nonNull(contactInformation)) {
                contactInformationRepository.deleteById(contactInformationId);
                log.info("deleteContactInformationById {}", contactInformation);
            }
        }
    }

}