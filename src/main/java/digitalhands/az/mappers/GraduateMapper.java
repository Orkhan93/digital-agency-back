package digitalhands.az.mappers;

import digitalhands.az.entity.Graduate;
import digitalhands.az.request.GraduateRequest;
import digitalhands.az.response.GraduateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GraduateMapper {

    GraduateResponse fromRequestToResponse(GraduateRequest graduateRequest);

    Graduate fromRequestToModel(GraduateRequest graduateRequest);

    GraduateResponse fromModelToResponse(Graduate graduate);

}