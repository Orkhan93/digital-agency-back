package digitalhands.az.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String code, String message) {
        super(message);
    }

}