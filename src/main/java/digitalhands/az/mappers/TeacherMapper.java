package digitalhands.az.mappers;

import digitalhands.az.entity.Teacher;
import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    Teacher fromRequestToModel(TeacherRequest teacherRequest);

    TeacherResponse fromModelToResponse(Teacher teacher);

}