package digitalhands.az.exception;

public class MaintenanceNotFoundException extends RuntimeException {

    public MaintenanceNotFoundException(String code, String message) {
        super(message);
    }

}