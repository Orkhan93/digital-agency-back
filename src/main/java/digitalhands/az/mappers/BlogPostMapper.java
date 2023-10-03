package digitalhands.az.mappers;

import digitalhands.az.entity.BlogPost;
import digitalhands.az.request.BlogPostRequest;
import digitalhands.az.response.BlogPostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogPostMapper {

    BlogPost fromRequestToModel(BlogPostRequest blogPostRequest);

    BlogPostResponse fromModelToResponse(BlogPost blogPost);


}