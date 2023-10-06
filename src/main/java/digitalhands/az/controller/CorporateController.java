package digitalhands.az.controller;

import digitalhands.az.request.CorporateRequest;
import digitalhands.az.response.CorporateResponse;
import digitalhands.az.service.CorporateService;
import digitalhands.az.wrapper.CorporateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corporate")
@RequiredArgsConstructor
public class CorporateController {

    private final CorporateService corporateService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<CorporateResponse> createCorporate(@RequestBody CorporateRequest corporateRequest,
                                                             @PathVariable(name = "userId") Long userId) {
        return corporateService.createCorporate(corporateRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<CorporateResponse> updateCorporate(@RequestBody CorporateRequest corporateRequest,
                                                             @PathVariable(name = "userId") Long userId) {
        return corporateService.updateCorporate(corporateRequest, userId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CorporateWrapper>> getAllCorporate() {
        return corporateService.getAllCorporate();
    }

    @GetMapping("/get/{corporateId}")
    public ResponseEntity<CorporateResponse> getCorporateById(@PathVariable(name = "corporateId") Long corporateId) {
        return corporateService.getCorporateById(corporateId);
    }

    @DeleteMapping("/{userId}/delete/{corporateId}")
    public void deleteCorporateById(@PathVariable(name = "userId") Long userId,
                                    @PathVariable(name = "corporateId") Long corporateId) {
        corporateService.deleteCorporateById(userId, corporateId);
    }

}