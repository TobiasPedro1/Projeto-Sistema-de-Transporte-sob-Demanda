package exceptions;

public class ClienteNaoValidadoException extends RuntimeException {
    public ClienteNaoValidadoException(String message) {
        super(message);
    }
}
