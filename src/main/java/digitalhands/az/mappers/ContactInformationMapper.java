package digitalhands.az.mappers;

import digitalhands.az.entity.ContactInformation;
import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactInformationMapper {

    ContactInformation fromRequestToModel(ContactInformationRequest contactInformationRequest);

    ContactInformationResponse fromModelToResponse(ContactInformation contactInformation);

    ContactInformationResponse fromRequestToResponse(ContactInformationRequest contactInformationRequest);

}