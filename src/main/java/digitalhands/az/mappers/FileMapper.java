package digitalhands.az.mappers;

import digitalhands.az.file_upload.entity.File;
import digitalhands.az.file_upload.request.FileRequest;
import digitalhands.az.file_upload.response.FileResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface FileMapper {

    FileRequest fromModelToRequest(File file);

    File fromRequestToModel(FileRequest fileRequest);

    FileResponse fromModelToResponse(File file);

}