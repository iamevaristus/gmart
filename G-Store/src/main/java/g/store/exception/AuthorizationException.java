package g.store.exception;

public class AuthorizationException extends Exception{
    public AuthorizationException(String string) {
        super(string);
    }

    public AuthorizationException() {
        super(ErrorStrings.AUTHORIZATION_ERROR);
    }
}
