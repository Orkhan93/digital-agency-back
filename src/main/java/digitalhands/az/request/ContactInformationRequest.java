package digitalhands.az.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactInformationRequest {

    private Long id;
    private String phone;
    private String content;
    private String email;
    private String address;

}