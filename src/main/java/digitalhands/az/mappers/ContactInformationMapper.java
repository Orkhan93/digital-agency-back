package digitalhands.az.mappers;

import digitalhands.az.entity.ContactInformation;
import digitalhands.az.request.ContactInformationRequest;
import digitalhands.az.response.ContactInformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactInformationMapper {

    ContactInformation fromRequestToModel(ContactInformationRequest contactInformationRequest);

    ContactInformationResponse fromModelToResponse(ContactInformation contactInformation);

    ContactInformationResponse fromRequestToResponse(ContactInformationRequest contactInformationRequest);

    List<ContactInformationResponse> fromModelListToResponseList(List<ContactInformation> contactInformation);

}