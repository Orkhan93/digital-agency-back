package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateResponse {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String imageData;
    private Long categoryId;

}