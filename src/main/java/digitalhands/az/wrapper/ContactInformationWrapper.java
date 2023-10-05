package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformationWrapper {

    private Long id;
    private String name;
    private String phone;
    private String content;
    private String email;
    private String address;
    private Long contactId;

}