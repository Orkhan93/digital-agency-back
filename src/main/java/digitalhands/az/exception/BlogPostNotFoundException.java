package digitalhands.az.exception;

public class BlogPostNotFoundException extends RuntimeException {

    public BlogPostNotFoundException(String message) {
        super(message);
    }
}