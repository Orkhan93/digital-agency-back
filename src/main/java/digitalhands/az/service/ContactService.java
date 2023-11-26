package digitalhands.az.service;

import digitalhands.az.entity.Contact;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.ContactNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.ContactMapper;
import digitalhands.az.repository.ContactRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.ContactRequest;
import digitalhands.az.response.ContactResponse;
import digitalhands.az.wrapper.ContactWrapper;
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
public class ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactMapper contactMapper;

    public ResponseEntity<ContactResponse> createContact(ContactRequest contactRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(contactMapper.fromModelToResponse
                            (contactRepository.save(contactMapper.fromRequestToModel(contactRequest))));
        } else
            throw new UnauthorizedUserException(ErrorMessage.UNAUTHORIZED_USER);
    }

    public ResponseEntity<ContactResponse> updateContact(ContactRequest contactRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Contact contact = contactRepository.findById(contactRequest.getId()).orElseThrow(
                    () -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND));
            if (Objects.nonNull(contact)) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(contactMapper.fromModelToResponse
                                (contactRepository.save(contactMapper.fromRequestToModel(contactRequest))));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public ResponseEntity<List<ContactWrapper>> getAllContact() {
        return ResponseEntity.status(HttpStatus.OK).body(contactRepository.getAllContacts());
    }

    public ResponseEntity<ContactResponse> getContactById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND));
        return ResponseEntity.status(HttpStatus.OK).body(contactMapper.fromModelToResponse(contact));
    }

    public void deleteContact(Long userId, Long contactId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Contact contact = contactRepository.findById(contactId).orElseThrow(
                    () -> new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND));
            if (Objects.nonNull(contact)) {
                contactRepository.deleteById(contactId);
                log.info("deleteContact {}", contact);
            }
        }
    }

}