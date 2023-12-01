package digitalhands.az.exception;

public class CorporateNotFoundException extends RuntimeException {

    public CorporateNotFoundException(String code, String message) {
        super(message);
    }

}