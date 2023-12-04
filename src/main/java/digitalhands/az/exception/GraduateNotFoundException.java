package digitalhands.az.exception;

public class GraduateNotFoundException extends RuntimeException {

    public GraduateNotFoundException(String code, String message) {
        super(message);
    }

}