package digitalhands.az.controller;

import digitalhands.az.request.GraduateRequest;
import digitalhands.az.response.GraduateResponse;
import digitalhands.az.response.GraduateResponseList;
import digitalhands.az.service.GraduateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graduate")
@RequiredArgsConstructor
public class GraduateController {

    private final GraduateService graduateService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<GraduateResponse> createGraduate(@RequestBody GraduateRequest graduateRequest,
                                                           @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(graduateService.createGraduate(graduateRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<GraduateResponse> updateGraduate(@RequestBody GraduateRequest graduateRequest,
                                                           @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(graduateService.updateGraduate(graduateRequest, userId));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<GraduateResponse> getGraduateById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(graduateService.getGraduateById(userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<GraduateResponseList> getAllGraduates() {
        return ResponseEntity.status(HttpStatus.OK).body(graduateService.getALlGraduates());
    }

    @DeleteMapping("/{userId}/delete/{graduateId}")
    public void deleteGraduate(@PathVariable(name = "userId") Long userId,
                               @PathVariable(name = "graduateId") Long graduateId) {
        graduateService.deleteGraduate(userId, graduateId);
    }

}