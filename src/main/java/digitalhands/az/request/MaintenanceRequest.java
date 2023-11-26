package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceRequest {

    private Long id;
    private String title;
    private String content;
    private String imageData;

}