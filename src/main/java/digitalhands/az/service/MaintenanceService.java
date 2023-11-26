package digitalhands.az.service;

import digitalhands.az.entity.Maintenance;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.MaintenanceNotFoundException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.MaintenanceMapper;
import digitalhands.az.repository.MaintenanceRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.MaintenanceRequest;
import digitalhands.az.response.MaintenanceResponse;
import digitalhands.az.wrapper.MaintenanceWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final UserRepository userRepository;
    private final MaintenanceMapper maintenanceMapper;

    public ResponseEntity<MaintenanceResponse> createMaintenance(MaintenanceRequest maintenanceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(maintenanceMapper.fromModelToResponse(maintenanceRepository
                            .save(maintenanceMapper.fromRequestToModel(maintenanceRequest))));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<MaintenanceResponse> getMaintenanceById(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(ErrorMessage.MAINTENANCE_NOT_FOUND));
        if (Objects.nonNull(maintenance)) {
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceMapper.fromModelToResponse(maintenance));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<List<MaintenanceWrapper>> getAllMaintenances() {
        return ResponseEntity.status(HttpStatus.OK).body(maintenanceRepository.getAllMaintenances());
    }

    public ResponseEntity<MaintenanceResponse> updateMaintenance(MaintenanceRequest maintenanceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Maintenance maintenance = maintenanceRepository.findById(maintenanceRequest.getId())
                    .orElseThrow(() -> new MaintenanceNotFoundException(ErrorMessage.MAINTENANCE_NOT_FOUND));
            if (Objects.nonNull(maintenance)) {
                return ResponseEntity.status(HttpStatus.OK).body(maintenanceMapper.fromModelToResponse
                        (maintenanceRepository.save(maintenanceMapper.fromRequestToModel(maintenanceRequest))));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public void deleteMaintenance(Long userId, Long maintenanceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN))
        {
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                    .orElseThrow(() -> new MaintenanceNotFoundException(ErrorMessage.MAINTENANCE_NOT_FOUND));
            if (Objects.nonNull(maintenance)) {
                maintenanceRepository.delete(maintenance);
            }
        }
    }

}