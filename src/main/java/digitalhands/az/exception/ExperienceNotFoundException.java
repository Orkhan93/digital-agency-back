package digitalhands.az.exception;

public class ExperienceNotFoundException extends RuntimeException {

    public ExperienceNotFoundException(String code, String message) {
        super(message);
    }

}