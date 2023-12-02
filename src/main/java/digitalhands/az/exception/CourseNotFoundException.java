package digitalhands.az.exception;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String code, String message) {
        super(message);
    }

}