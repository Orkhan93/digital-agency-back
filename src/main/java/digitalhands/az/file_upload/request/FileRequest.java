package digitalhands.az.file_upload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequest {

    private String name;
    private String contentType;
    private byte[] fileData;

}