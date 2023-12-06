package digitalhands.az.service;

import digitalhands.az.entity.Maintenance;
import digitalhands.az.entity.User;
import digitalhands.az.enums.UserRole;
import digitalhands.az.exception.MaintenanceNotFoundException;
import digitalhands.az.exception.UnauthorizedUserException;
import digitalhands.az.exception.UserNotFoundException;
import digitalhands.az.exception.errors.ErrorMessage;
import digitalhands.az.mappers.MaintenanceMapper;
import digitalhands.az.repository.MaintenanceRepository;
import digitalhands.az.repository.UserRepository;
import digitalhands.az.request.MaintenanceRequest;
import digitalhands.az.response.MaintenanceResponse;
import digitalhands.az.response.MaintenanceResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final UserRepository userRepository;
    private final MaintenanceMapper maintenanceMapper;

    public MaintenanceResponse createMaintenance(MaintenanceRequest maintenanceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            return maintenanceMapper.fromModelToResponse(maintenanceRepository
                    .save(maintenanceMapper.fromRequestToModel(maintenanceRequest)));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public MaintenanceResponse getMaintenanceById(Long maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new MaintenanceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.MAINTENANCE_NOT_FOUND));
        return maintenanceMapper.fromModelToResponse(maintenance);
    }

    public MaintenanceResponseList getAllMaintenances() {
        List<Maintenance> all = maintenanceRepository.findAll();
        MaintenanceResponseList list = new MaintenanceResponseList();
        List<MaintenanceResponse> maintenanceResponses = maintenanceMapper.fromModelListToResponseList(all);
        list.setMaintenanceResponses(maintenanceResponses);
        return list;
    }

    public MaintenanceResponse updateMaintenance(MaintenanceRequest maintenanceRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Maintenance maintenance = maintenanceRepository.findById(maintenanceRequest.getId())
                    .orElseThrow(() -> new MaintenanceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.MAINTENANCE_NOT_FOUND));
            if (Objects.isNull(maintenance)) {
                throw new MaintenanceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.MAINTENANCE_NOT_FOUND);
            }
            return maintenanceMapper.fromModelToResponse
                    (maintenanceRepository.save(maintenanceMapper.fromRequestToModel(maintenanceRequest)));
        }
        throw new UnauthorizedUserException(HttpStatus.UNAUTHORIZED.name());
    }

    public void deleteMaintenance(Long userId, Long maintenanceId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
        if (Objects.nonNull(user) && user.getUserRole().equals(UserRole.ADMIN)) {
            Maintenance maintenance = maintenanceRepository.findById(maintenanceId)
                    .orElseThrow(() -> new MaintenanceNotFoundException(HttpStatus.NOT_FOUND.name(), ErrorMessage.MAINTENANCE_NOT_FOUND));
            if (Objects.nonNull(maintenance)) {
                maintenanceRepository.delete(maintenance);
            }
        }
    }

}