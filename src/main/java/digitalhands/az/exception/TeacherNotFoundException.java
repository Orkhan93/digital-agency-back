package digitalhands.az.exception;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(String code, String message) {
        super(message);
    }

}