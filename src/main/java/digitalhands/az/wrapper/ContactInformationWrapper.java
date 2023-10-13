package digitalhands.az.wrapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformationWrapper {

    private String phone;
    private String content;
    private String email;
    private String address;

}