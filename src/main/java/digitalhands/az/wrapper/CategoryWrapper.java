package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWrapper {

    private Long id;
    private String name;
    private Long collectionId;

}