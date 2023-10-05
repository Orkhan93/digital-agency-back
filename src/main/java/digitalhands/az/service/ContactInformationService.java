package digitalhands.az.service;

import digitalhands.az.entity.Contact;
import digitalhands.az.entity.ContactInformation;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ContactInformationNotFoundException;
import digitalhands.az.exception.ContactNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.ContactInformationMapper;
import digitalhands.az.repository.ContactInformationRepository;
import digitalhands.az.repository.ContactRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import digitalhands.az.wrapper.ContactInformationWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactInformationMapper contactInformationMapper;

    public ResponseEntity<ContactInformationResponse> createContactInformation
            (ContactInformationRequest contactInformationRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Contact contact = contactRepository.findById(contactInformationRequest.getContactId())
                    .orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND));
            ContactInformation contactInformation =
                    contactInformationMapper.fromRequestToModel(contactInformationRequest);
            contactInformation.setContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(contactInformationMapper.fromModelToResponse(contactInformation));
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
                Contact contact = contactRepository.findById(contactInformationRequest.getContactId())
                        .orElseThrow(() -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND));
                ContactInformation updated = contactInformationMapper.fromRequestToModel(contactInformationRequest);
                updated.setContact(contact);
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
        userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        contactInformationRepository.findById(contactInformationId)
                .orElseThrow(() -> new ContactInformationNotFoundException(ErrorMessage.CONTACT_INFORMATION_NOT_FOUND));
        contactInformationRepository.deleteById(contactInformationId);
    }

}