package digitalhands.az.mappers;

import digitalhands.az.entity.Collection;
import digitalhands.az.request.CollectionRequest;
import digitalhands.az.response.CollectionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionMapper {

    CollectionResponse fromRequestToResponse(CollectionRequest collectionRequest);

    Collection fromRequestToModel(CollectionRequest collectionRequest);

    CollectionResponse fromModelToResponse(Collection Collection);

    List<CollectionResponse> fromModelListToResponseList(List<Collection> collections);

}