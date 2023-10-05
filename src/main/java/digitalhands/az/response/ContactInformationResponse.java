package digitalhands.az.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactInformationResponse {

    private Long id;
    private String name;
    private String phone;
    private String content;
    private String email;
    private String address;
    private Long contactId;

}