package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryRequest {

    private Long id;
    private String name;
    private Long collectionId;

}