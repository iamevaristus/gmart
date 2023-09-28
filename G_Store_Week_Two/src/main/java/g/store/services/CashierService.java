package g.store.services;

import g.store.exception.CartException;
import g.store.exception.PaymentException;
import g.store.model.transactions.Receipt;
import g.store.model.entities.Customer;
import g.store.model.transactions.Product;
import g.store.enums.auth.ActionStatus;
import g.store.enums.transactions.PaymentMethod;
import g.store.enums.transactions.PaymentResult;

import java.util.List;

public interface CashierService {
    /**
     * Verifies the customer and the product.
     * @param product Product the customer wants to buy.
     * @param age Age of the customer.
     * @return True or False
     * @throws CartException on error
     */
    public boolean verifyIfCustomerIsEligibleToBuyProduct(Product product, int age) throws CartException;

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
    public boolean updateStoreData(double customerAmount, List<Product> products) throws PaymentException;

    /**
     * This method is used to give receipt of product and payment to the Customer after a successful transaction
     * @param customer This is the customer who is making the transaction
     * @return Receipt of transaction
     */
    public Receipt dispenseReceipt(Customer customer, PaymentMethod paymentMethod);
}
