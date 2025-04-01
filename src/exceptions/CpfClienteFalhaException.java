package exceptions;

public class CpfClienteFalhaException extends RuntimeException {
    public CpfClienteFalhaException(String message) {
        super(message);
    }
}
