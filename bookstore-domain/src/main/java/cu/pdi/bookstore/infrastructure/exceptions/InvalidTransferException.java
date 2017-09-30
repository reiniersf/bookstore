package cu.pdi.bookstore.infrastructure.exceptions;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(String message) {
        super(message);
    }
}
