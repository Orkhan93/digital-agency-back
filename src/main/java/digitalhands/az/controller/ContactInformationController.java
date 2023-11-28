package digitalhands.az.controller;

import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import digitalhands.az.response.ContactInformationResponseList;
import digitalhands.az.service.ContactInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact-information")
@RequiredArgsConstructor
public class ContactInformationController {

    private final ContactInformationService contactInformationService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ContactInformationResponse> createContactInformation
            (@RequestBody ContactInformationRequest contactInformationRequest,
             @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contactInformationService.createContactInformation(contactInformationRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ContactInformationResponse> updateContactInformation(
            @RequestBody ContactInformationRequest contactInformationRequest,
            @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contactInformationService.updateContactInformation(contactInformationRequest, userId));
    }

    @GetMapping("/get/{contactInformationId}")
    public ResponseEntity<ContactInformationResponse> getContactInformationById
            (@PathVariable(name = "contactInformationId") Long contactInformationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contactInformationService.getContactInformationById(contactInformationId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ContactInformationResponseList> getAllContactInformation() {
        return ResponseEntity.status(HttpStatus.OK).body(contactInformationService.getAllContactInformation());
    }

    @DeleteMapping("/{userId}/delete/{contactInformationId}")
    public void deleteContactInformationById(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "contactInformationId") Long contactInformationId) {
        contactInformationService.deleteContactInformationById(userId, contactInformationId);
    }

}