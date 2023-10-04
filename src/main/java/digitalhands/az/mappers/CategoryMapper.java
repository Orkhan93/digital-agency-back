package digitalhands.az.mappers;

import digitalhands.az.entity.Category;
import digitalhands.az.request.CategoryRequest;
import digitalhands.az.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryResponse fromRequestToResponse(CategoryRequest categoryRequest);

    Category fromRequestToModel(CategoryRequest categoryRequest);

    CategoryResponse fromModelToResponse(Category category);

}