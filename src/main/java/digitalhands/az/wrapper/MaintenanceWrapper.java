package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceWrapper {

    private Long id;
    private String title;
    private String content;
    private String imageData;

}