package g.store.exception;

public class CartException extends Exception{
    public CartException(String string) {
        super(string);
    }

    public CartException() {
        super(ErrorStrings.CART_ERROR);
    }
}
