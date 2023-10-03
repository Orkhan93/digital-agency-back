package digitalhands.az.mappers;

import digitalhands.az.entity.User;
import digitalhands.az.request.UserRequest;
import digitalhands.az.request.UserSignUpRequest;
import digitalhands.az.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User fromUserSignUpRequestToModel(UserSignUpRequest userSignUpRequest);

    UserRequest fromModelToUserRequest(User user);

    UserResponse fromModelToResponse(User user);

}