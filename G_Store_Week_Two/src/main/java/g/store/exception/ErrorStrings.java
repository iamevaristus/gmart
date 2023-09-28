package g.store.exception;

public class ErrorStrings {
    /// PAYMENT ERROR STATEMENTS
    public static String CASH_PAYMENT_METHOD_FAILED = "The amount of money you have given does not" +
            " equal your product's total amount.";

    public static String PAYMENT_FAILED = "Cannot complete transactions.";

    public static String CASH_EMPTY = "Cannot proceed with transaction, there is no cash";

    public static String CANNOT_PAY_SALARY = "G-Store cannot pay salary to cashier due to low income from store";

    /// CART ERROR STATEMENTS
    public static String CANNOT_BUY_PRODUCT_DUE_TO_AGE_RESTRICTIONS = "Your age does not meet with the required" +
            " age limit for this product.";

    public static String NO_PRODUCT_IN_CUSTOMER_PRODUCT_LIST = "Cannot remove product that is not in your cart";

    /// AUTHORIZATION STATEMENTS

    public static String AUTHORIZATION_ERROR = "Authorization not granted.";


    /// STORE EXCEPTION

    public static String OUT_OF_STOCK = "Out of stock";

    public static String STORE_EXCEPTION = "G-Store Exception!";
}
