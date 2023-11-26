package digitalhands.az.service;

import digitalhands.az.entity.ContactInformation;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ContactInformationNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.ContactInformationMapper;
import digitalhands.az.repository.ContactInformationRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import digitalhands.az.wrapper.ContactInformationWrapper;
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
public class ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    private final UserRepository userRepository;
    private final ContactInformationMapper contactInformationMapper;

    public ResponseEntity<ContactInformationResponse> createContactInformation
            (ContactInformationRequest contactInformationRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(contactInformationMapper.fromModelToResponse(contactInformationRepository
                            .save(contactInformationMapper.fromRequestToModel(contactInformationRequest))));
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<ContactInformationResponse> updateContactInformation
            (ContactInformationRequest contactInformationRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            ContactInformation contactInformation = contactInformationRepository.findById(contactInformationRequest.getId())
                    .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
            if (Objects.nonNull(contactInformation)) {
                ContactInformation updated = contactInformationMapper.fromRequestToModel(contactInformationRequest);
                return ResponseEntity.status(HttpStatus.OK).body(contactInformationMapper.fromModelToResponse(
                        contactInformationRepository.save(updated)));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<ContactInformationResponse> getContactInformationById(Long contactInformationId) {
        ContactInformation contactInformation = contactInformationRepository.findById(contactInformationId)
                .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
        if (Objects.nonNull(contactInformation)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(contactInformationMapper.fromModelToResponse(contactInformation));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    public ResponseEntity<List<ContactInformationWrapper>> getAllContactInformation() {
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationRepository.getAllContactInformation());
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