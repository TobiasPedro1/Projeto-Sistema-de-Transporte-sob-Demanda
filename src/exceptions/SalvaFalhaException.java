package exceptions;

public class SalvaFalhaException extends RuntimeException {
    public SalvaFalhaException(String message, Throwable cause) {
        super(message, cause);
    }
}
