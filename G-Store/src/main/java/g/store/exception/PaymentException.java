package g.store.exception;

public class PaymentException extends Exception{
    public PaymentException(String string) {
        super(string);
    }

    public PaymentException() {
        super(ErrorStrings.PAYMENT_FAILED);
    }
}
