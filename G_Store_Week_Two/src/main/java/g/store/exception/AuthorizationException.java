package g.store.exception;

public class AuthorizationException extends Exception{

    public AuthorizationException() {
        super(ErrorStrings.AUTHORIZATION_ERROR);
    }
}
