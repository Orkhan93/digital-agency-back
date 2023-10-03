package digitalhands.az.repository;

import digitalhands.az.entity.Experience;
import digitalhands.az.request.ExperienceRequest;
import digitalhands.az.response.ExperienceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExperienceMapper {


    ExperienceResponse fromModelToResponse(Experience experience);

    ExperienceResponse fromRequestToResponse(ExperienceRequest experienceRequest);

    Experience fromRequestToModel(ExperienceRequest experienceRequest);

}