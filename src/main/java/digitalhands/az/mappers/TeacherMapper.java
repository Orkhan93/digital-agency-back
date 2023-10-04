package digitalhands.az.mappers;

import digitalhands.az.entity.BlogPost;
import digitalhands.az.entity.Teacher;
import digitalhands.az.request.BlogPostRequest;
import digitalhands.az.request.TeacherRequest;
import digitalhands.az.response.BlogPostResponse;
import digitalhands.az.response.TeacherResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {

    Teacher fromRequestToModel(TeacherRequest teacherRequest);

    TeacherResponse fromModelToResponse(Teacher teacher);
    List<TeacherResponse> fromModelToResponseList(List<Teacher> teachers);

}