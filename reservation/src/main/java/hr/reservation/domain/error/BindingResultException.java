package hr.reservation.domain.error;


public class BindingResultException extends RuntimeException{
    public BindingResultException(String message) {
        super(message);
    }
}
