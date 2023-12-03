package digitalhands.az.controller;

import digitalhands.az.request.ExperienceRequest;
import digitalhands.az.response.ExperienceResponse;
import digitalhands.az.response.ExperienceResponseList;
import digitalhands.az.service.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<ExperienceResponse> createExperience(@RequestBody ExperienceRequest experienceRequest,
                                                               @PathVariable(name = "userId") Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(experienceService.createExperience(experienceRequest, userId));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ExperienceResponse> updateExperience(@RequestBody ExperienceRequest experienceRequest,
                                                               @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(experienceService.updateExperience(experienceRequest, userId));
    }

    @GetMapping("/get/{experienceId}")
    public ResponseEntity<ExperienceResponse> getExperienceById(@PathVariable(name = "experienceId") Long experienceId) {
        return ResponseEntity.status(HttpStatus.OK).body(experienceService.getExperienceById(experienceId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<ExperienceResponseList> getAllExperiences() {
        return ResponseEntity.status(HttpStatus.OK).body(experienceService.getAllExperiences());
    }

    @DeleteMapping("/{userId}/delete/{experienceId}")
    public void deleteExperience(@PathVariable(name = "userId") Long userId,
                                 @PathVariable(name = "experienceId") Long experienceId) {
        experienceService.deleteExperience(userId, experienceId);
    }

}