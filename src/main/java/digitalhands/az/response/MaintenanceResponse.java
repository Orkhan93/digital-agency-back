package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceResponse {

    private Long id;
    private String title;
    private String content;
    private String imageData;

}