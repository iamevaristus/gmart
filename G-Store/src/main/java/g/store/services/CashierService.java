package g.store.services;

import g.store.exception.CartException;
import g.store.exception.PaymentException;
import g.store.model.transactions.Receipt;
import g.store.model.entities.Customer;
import g.store.model.transactions.Product;
import g.store.types.AuthenticationTypes;
import g.store.types.payment_types.PaymentMethod;
import g.store.types.payment_types.PaymentResultEnum;

import java.util.List;

public interface CashierService {
    /**
     * This method will check if the customer will buy a product.
     * This is mainly for products that have age restrictions like cigar, alcohol, etc.
     * @return True if the customer can buy, else false.
     */
    public boolean verifyCustomer(Product product, int age) throws CartException;

    /**
     * This gets the total amount of products in a Customer's cart
     * @param products List of Products
     * @return Total Amount in double
     */
    public double calculateCustomerProducts(List<Product> products);

    /**
     * This method asks the customer to choose a payment option
     * @return PaymentMethod as an enum
     */
    public PaymentMethod requestPaymentMethodFromCustomer(String option);

    /**
     * This method will verify if the cash amount provided by the Customer is equal to the cart amount
     * @param customerAmount The amount from the Customer
     * @param products The list of Customer's products
     * @return True, if it is equals.
     */
    public boolean verifyCash(double customerAmount, List<Product> products) throws PaymentException;

    /**
     * This is method that will verify the cart if payment is made or not
     * @param result This is the PAYMENT-RESULT ENUM
     * @return It will return true if transaction was successful
     */
    public boolean verifyATM(PaymentResultEnum result, List<Product> products) throws PaymentException;

    /**
     * This notifies the Customer about any error during payment transaction
     * @return True or False if transaction is successful
     */
    public boolean errorInCheckout();

    /**
     * This method is used to give receipt of product and payment to the Customer after a successful transaction
     * @param customer This is the customer who is making the transaction
     * @return Receipt of transaction
     */
    public Receipt dispenseReceipt(Customer customer);

    /**
     * This finishes the checkout from the Customer's cart
     * @return Authentication as successful or not
     */
    public AuthenticationTypes finishCheckout();
}
