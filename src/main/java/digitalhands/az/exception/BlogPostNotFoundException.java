package digitalhands.az.exception;

public class BlogPostNotFoundException extends RuntimeException {

    public BlogPostNotFoundException(String code, String message) {
        super(message);
    }

}