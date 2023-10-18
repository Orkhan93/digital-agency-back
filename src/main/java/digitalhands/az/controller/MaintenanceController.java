package digitalhands.az.controller;

import digitalhands.az.request.MaintenanceRequest;
import digitalhands.az.response.MaintenanceResponse;
import digitalhands.az.service.MaintenanceService;
import digitalhands.az.wrapper.MaintenanceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<MaintenanceResponse> addMaintenance(@RequestBody MaintenanceRequest maintenanceRequest,
                                                              @PathVariable(name = "userId") Long userId) {
        return maintenanceService.createMaintenance(maintenanceRequest, userId);
    }

    @GetMapping("/get/{maintenanceId}")
    public ResponseEntity<MaintenanceResponse> getMaintenanceById(@PathVariable(name = "maintenanceId") Long maintenanceId) {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MaintenanceWrapper>> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<MaintenanceResponse> updateMaintenance(@RequestBody MaintenanceRequest maintenanceRequest,
                                                                 @PathVariable(name = "userId") Long userId) {
        return maintenanceService.updateMaintenance(maintenanceRequest, userId);
    }

    @DeleteMapping("/{userId}/delete/{maintenanceId}")
    public void deleteMaintenance(@PathVariable(name = "userId") Long userId,
                                  @PathVariable(name = "maintenanceId") Long maintenanceId) {
        maintenanceService.deleteMaintenance(userId, maintenanceId);
    }

}