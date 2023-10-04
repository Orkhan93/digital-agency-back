package digitalhands.az.request;

import lombok.Data;

@Data
public class CategoryRequest {

    private Long id;
    private String name;
    private Long collectionId;

}