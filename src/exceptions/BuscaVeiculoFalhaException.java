package exceptions;

public class BuscaVeiculoFalhaException extends RuntimeException {
    public BuscaVeiculoFalhaException(String message) {
        super(message);
    }
}
