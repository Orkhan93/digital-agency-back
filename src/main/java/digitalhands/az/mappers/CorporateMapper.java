package digitalhands.az.mappers;

import digitalhands.az.entity.Corporate;
import digitalhands.az.request.CorporateRequest;
import digitalhands.az.response.CorporateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CorporateMapper {

    Corporate fromRequestToModel(CorporateRequest corporateRequest);

    CorporateResponse fromModelToResponse(Corporate corporate);

    CorporateResponse fromRequestToResponse(CorporateRequest corporateRequest);

}