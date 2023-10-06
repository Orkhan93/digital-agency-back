package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateWrapper {

    private Long id;
    private String name;
    private String title;
    private String content;
    private String imageData;
    private Long categoryId;

}