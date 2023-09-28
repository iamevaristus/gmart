package g.store.exception;

public class ErrorStrings {
    /// PAYMENT ERROR STATEMENTS
    public static String BANK_PAYMENT_METHOD_FAILED = "There is a transaction error from your bank." +
            " Please contact your bank or change your payment method.";

    public static String CASH_PAYMENT_METHOD_FAILED = "The amount of money you have given does not" +
            " equal your product's total amount.";

    public static String PAYMENT_FAILED = "Cannot complete transactions.";

    /// CART ERROR STATEMENTS
    public static String CANNOT_BUY_PRODUCT_DUE_TO_AGE_RESTRICTIONS = "Your age does not meet with the required" +
            " age limit for this product.";

    public static String CART_ERROR = "Fatal cart error. Please report to the manager.";

    /// AUTHORIZATION STATEMENTS
    public static String NO_AUTHORIZATION_STATUS = "You do not have the authorization required to access this.";

    public static String ERR_IN_AUTHORIZATION = "There is an authorization error. Please verify yourself.";

    public static String AUTHORIZATION_ERROR = "Authorization not granted.";
}
