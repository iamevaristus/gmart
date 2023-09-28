package g.store.model.entities;

import g.store.exception.ErrorStrings;
import g.store.exception.PaymentException;
import g.store.model.transactions.Receipt;
import g.store.services.CustomerService;
import g.store.model.transactions.Product;
import g.store.model.transactions.Cart;

import java.util.*;

/**
 * This is a class of Customer for the G-Store. The customer has the following params:
 *
 *
 * @author G-Store
 */
public class Customer extends Cart implements CustomerService {
    // UUID Customer ID
    private UUID customerId;

    // CASH AT HAND
    private double cashAtHand;

    // Receipt from store
    private Receipt receipt;

    // Change from transaction
    private double change;

    // Name of the customer
    private String name;

//    public double getChange() {
//        return change;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChange(double change) {
        this.change = change;
    }

//    public Receipt getReceipt() {
//        return receipt;
//    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

//    public Customer(
//            List<Product> products,
//            UUID customerId,
//            double change,
//            Receipt receipt
//    ) {
//        super(products);
//        this.change = change;
//        this.receipt = receipt;
//        this.customerId = customerId;
//    }

    public Customer(
            List<Product> products,
            UUID customerId,
            double cashAtHand
    ) {
        super(products);
        this.customerId = customerId;
        this.cashAtHand = cashAtHand;
    }

//    public double getCashAtHand() {
//        return cashAtHand;
//    }
//
//    public void setCashAtHand(double cashAtHand) {
//        this.cashAtHand = cashAtHand;
//    }
//
//    public UUID getCustomerId() {
//        return customerId;
//    }
//
//    public void setCustomerId(UUID customerId) {
//        this.customerId = customerId;
//    }

    protected String pay(String cash) throws PaymentException {
        /// TODO: PAY WITH CASH
        if(cash.isEmpty()) {
            throw new PaymentException(ErrorStrings.CASH_EMPTY);
        } else {
            return cash;
        }
    }

    @Override
    public String toString() {
        String header = "This is the information on the customer.\n";

        String customerInfo =
                "ID of Customer: " + customerId.toString() + "\n" +
                "Customer cash at hand: " + cashAtHand + "\n";
        return header + customerInfo;
    }

    @Override
    public String toStringWithCart() {
        String cartInfo = this.getProducts().toString();
        String header = "This is the information on the customer.\n";

        String customerInfo =
                "ID of Customer: " + customerId.toString() + "\n" +
                "Customer cash at hand: " + cashAtHand + "\n";
        return header + customerInfo + cartInfo;
    }

//    @Override
//    public ActionStatus giveFeedback(Feedback complaint) {
//        return null;
//    }
//
//    @Override
//    public ComplaintStatus resolveComplaints(Feedback complaint) throws AuthorizationException {
//        throw new AuthorizationException();
//    }
//
//    @Override
//    public ComplaintStatus escalateComplaints(Feedback complaint) throws AuthorizationException {
//        throw new AuthorizationException();
//    }
//
//    @Override
//    public ComplaintStatus pendComplaints(Feedback complaint) throws AuthorizationException {
//        throw new AuthorizationException();
//    }

    @Override
    public void collectReceipt(Receipt receipt) {
        setReceipt(receipt);
    }
}
