package digitalhands.az.mappers;

import digitalhands.az.entity.Contact;
import digitalhands.az.request.ContactRequest;
import digitalhands.az.response.ContactResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    Contact fromRequestToModel(ContactRequest contactRequest);

    ContactResponse fromModelToResponse(Contact contact);

    ContactResponse fromRequestToResponse(ContactRequest contactRequest);

}