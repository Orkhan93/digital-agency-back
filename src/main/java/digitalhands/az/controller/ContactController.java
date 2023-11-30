package digitalhands.az.controller;

import digitalhands.az.request.ContactRequest;
import digitalhands.az.response.ContactResponse;
import digitalhands.az.response.ContactResponseList;
import digitalhands.az.service.ContactService;
import digitalhands.az.wrapper.ContactWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ContactResponse> createContact(@RequestBody ContactRequest contactRequest,
                                                         @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContact(contactRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ContactResponse> updateContact(@RequestBody ContactRequest contactRequest,
                                                         @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.updateContact(contactRequest, userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ContactResponseList> getAllContacts() {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getAllContact());
    }

    @GetMapping("/get/{contactId}")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable(name = "contactId") Long contactId) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.getContactById(contactId));
    }

    @DeleteMapping("/{userId}/delete/{contactId}")
    public void deleteContact(@PathVariable(name = "userId") Long userId,
                              @PathVariable(name = "contactId") Long contactId) {
        contactService.deleteContact(userId, contactId);

    }

}