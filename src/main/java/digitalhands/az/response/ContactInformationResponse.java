package digitalhands.az.response;

import lombok.Data;

@Data
public class ContactInformationResponse {

    private Long id;
    private String name;
    private String phone;
    private String content;
    private String email;
    private String address;
    private Long contactId;

}