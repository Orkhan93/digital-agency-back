package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateRequest {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String imageData;
    private Long collectionId;

}