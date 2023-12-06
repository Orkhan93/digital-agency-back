package digitalhands.az.mappers;

import digitalhands.az.entity.Maintenance;
import digitalhands.az.request.MaintenanceRequest;
import digitalhands.az.response.MaintenanceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MaintenanceMapper {

    Maintenance fromRequestToModel(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse fromRequestToResponse(MaintenanceRequest maintenanceRequest);

    MaintenanceResponse fromModelToResponse(Maintenance maintenance);

    List<MaintenanceResponse> fromModelListToResponseList(List<Maintenance> maintenances);

}