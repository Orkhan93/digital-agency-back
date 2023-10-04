package digitalhands.az.controller;

import digitalhands.az.request.GraduateRequest;
import digitalhands.az.response.GraduateResponse;
import digitalhands.az.service.GraduateService;
import digitalhands.az.wrapper.GraduateWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graduate")
@RequiredArgsConstructor
public class GraduateController {

    private final GraduateService graduateService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<GraduateResponse> createGraduate(@RequestBody GraduateRequest graduateRequest,
                                                           @PathVariable(name = "userId") Long userId) {
        return graduateService.createGraduate(graduateRequest, userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<GraduateResponse> updateGraduate(@RequestBody GraduateRequest graduateRequest,
                                                           @PathVariable(name = "userId") Long userId) {
        return graduateService.updateGraduate(graduateRequest, userId);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<GraduateResponse> getGraduateById(@PathVariable(name = "userId") Long userId) {
        return graduateService.getGraduateById(userId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GraduateWrapper>> getAllGraduates() {
        return graduateService.getALlGraduates();
    }

    @DeleteMapping("/{userId}/delete/{graduateId}")
    public void deleteGraduate(@PathVariable(name = "userId") Long userId,
                               @PathVariable(name = "graduateId") Long graduateId) {
        graduateService.deleteGraduate(userId, graduateId);
    }

}