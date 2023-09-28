package g.store.model.entities;

import g.store.exception.AuthorizationException;
import g.store.exception.ErrorStrings;
import g.store.exception.PaymentException;
import g.store.services.StoreFeedbackService;
import g.store.manager.Commons;
import g.store.model.others.Feedback;
import g.store.model.transactions.Product;
import g.store.model.transactions.Cart;
import g.store.types.AuthenticationTypes;
import g.store.types.ComplaintStatus;
import g.store.types.payment_types.PaymentResultEnum;

import java.util.*;

/**
 * This is a class of Customer for the G-Store. The customer has the following params:
 *
 *
 * @author G-Store
 */
public class Customer extends Cart implements StoreFeedbackService {
    // UUID Customer ID
    private UUID customerId;

    // EXTRA TIP
    private double tipAmount;

    public Customer(
            List<Product> products,
            UUID customerId,
            double tipAmount
    ) {
        super(products);
        this.customerId = customerId;
        this.tipAmount = tipAmount;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    protected PaymentResultEnum payWithATM() throws PaymentException {
        /// TODO: PAY WITH ATM
        return PaymentResultEnum.PAID;
    }

    protected String payWithCash() throws PaymentException {
        /// TODO: PAY WITH CASH
        Commons.debugAction(
                false,
                "Please enter the amount for your items..."
        );

        Scanner scanner = new Scanner(System.in);

        if(scanner.next().isEmpty()) {
            throw new PaymentException(ErrorStrings.CASH_PAYMENT_METHOD_FAILED);
        } else {
            return scanner.next();
        }
    }

    @Override
    public String toString() {
        String header = "This is the information on the customer.\n";

        String customerInfo =
                "ID of Customer: " + customerId.toString() + "\n" +
                "Tip Amount from Customer: " + tipAmount + "\n";
        return header + customerInfo;
    }

    @Override
    public String toStringWithCart() {
        String cartInfo = this.getProducts().toString();
        String header = "This is the information on the customer.\n";

        String customerInfo =
                "ID of Customer: " + customerId.toString() + "\n" +
                "Tip Amount from Customer: " + tipAmount + "\n";
        return header + customerInfo + cartInfo;
    }

    @Override
    public AuthenticationTypes giveFeedback(Feedback complaint) {
        return null;
    }

    @Override
    public ComplaintStatus resolveComplaints(Feedback complaint) throws AuthorizationException {
        throw new AuthorizationException();
    }

    @Override
    public ComplaintStatus escalateComplaints(Feedback complaint) throws AuthorizationException {
        throw new AuthorizationException();
    }

    @Override
    public ComplaintStatus pendComplaints(Feedback complaint) throws AuthorizationException {
        throw new AuthorizationException();
    }
}
