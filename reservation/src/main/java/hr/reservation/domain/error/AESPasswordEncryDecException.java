package hr.reservation.domain.error;

public class AESPasswordEncryDecException extends RuntimeException{
    public AESPasswordEncryDecException(String message) {
        super(message);
    }
}
