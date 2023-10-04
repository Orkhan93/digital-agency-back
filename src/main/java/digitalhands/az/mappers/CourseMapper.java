package digitalhands.az.mappers;

import digitalhands.az.entity.Course;
import digitalhands.az.request.CourseRequest;
import digitalhands.az.response.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    CourseResponse fromRequestToResponse(CourseRequest courseRequest);

    Course fromRequestToModel(CourseRequest CourseRequest);

    CourseResponse fromModelToResponse(Course Course);

}