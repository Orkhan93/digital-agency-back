package digitalhands.az.controller;

import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import digitalhands.az.service.ContactInformationService;
import digitalhands.az.wrapper.ContactInformationWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact-information")
@RequiredArgsConstructor
public class ContactInformationController {

    private final ContactInformationService contactInformationService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ContactInformationResponse> createContactInformation
            (@RequestBody ContactInformationRequest contactInformationRequest,
             @PathVariable(name = "userId") Long userId) {
        return contactInformationService.createContactInformation(contactInformationRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ContactInformationResponse> updateContactInformation(
            @RequestBody ContactInformationRequest contactInformationRequest,
            @PathVariable(name = "userId") Long userId) {
        return contactInformationService.updateContactInformation(contactInformationRequest, userId);
    }

    @GetMapping("/get/{contactInformationId}")
    public ResponseEntity<ContactInformationResponse> getContactInformationById
            (@PathVariable(name = "contactInformationId") Long contactInformationId) {
        return contactInformationService.getContactInformationById(contactInformationId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ContactInformationWrapper>> getAllContactInformation() {
        return contactInformationService.getAllContactInformation();
    }

    @DeleteMapping("/{userId}/delete/{contactInformationId}")
    public void deleteContactInformationById(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "contactInformationId") Long contactInformationId) {
        contactInformationService.deleteContactInformationById(userId, contactInformationId);
    }

}