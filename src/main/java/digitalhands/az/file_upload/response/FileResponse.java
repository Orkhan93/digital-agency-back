package digitalhands.az.file_upload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileResponse {

    private String name;
    private String contentType;
    private byte[] fileData;

}