package digitalhands.az.controller;

import digitalhands.az.request.CorporateRequest;
import digitalhands.az.response.CorporateResponse;
import digitalhands.az.response.CorporateResponseList;
import digitalhands.az.service.CorporateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/corporate")
@RequiredArgsConstructor
public class CorporateController {

    private final CorporateService corporateService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CorporateResponse> createCorporate(@RequestBody CorporateRequest corporateRequest,
                                                             @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(corporateService.createCorporate(corporateRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CorporateResponse> updateCorporate(@RequestBody CorporateRequest corporateRequest,
                                                             @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(corporateService.updateCorporate(corporateRequest, userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<CorporateResponseList> getAllCorporate() {
        return ResponseEntity.status(HttpStatus.OK).body(corporateService.getAllCorporate());
    }

    @GetMapping("/get/{corporateId}")
    public ResponseEntity<CorporateResponse> getCorporateById(@PathVariable(name = "corporateId") Long corporateId) {
        return ResponseEntity.status(HttpStatus.OK).body(corporateService.getCorporateById(corporateId));
    }

    @DeleteMapping("/{userId}/delete/{corporateId}")
    public void deleteCorporateById(@PathVariable(name = "userId") Long userId,
                                    @PathVariable(name = "corporateId") Long corporateId) {
        corporateService.deleteCorporateById(userId, corporateId);
    }

}