package g.store.model.entities;

import g.store.exception.CartException;
import g.store.exception.ErrorStrings;
import g.store.exception.PaymentException;
import g.store.services.CashierService;
import g.store.manager.Commons;
import g.store.manager.IDGenerators;
import g.store.model.transactions.Receipt;
import g.store.model.transactions.Product;
import g.store.types.*;
import g.store.types.payment_types.PaymentMethod;
import g.store.types.payment_types.PaymentResultEnum;
import g.store.types.staff_roles.StaffAuthority;
import g.store.types.staff_roles.StaffPosition;

import java.util.*;

public class Cashier extends Staff implements CashierService {

    public Cashier(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffPosition position,
            StaffAuthority authority,
            GenderTypes genderTypes,
            double salary
    ) {
        super(
                firstName,
                lastName,
                emailAddress,
                staffId,
                position,
                authority,
                genderTypes,
                salary
        );
    }

    @Override
    public boolean verifyCustomer(Product product, int age) throws CartException {
        for(String productName : notForMinor) {
            if(product.getProductName().equals(productName) && age < 18) {
                throw new CartException(ErrorStrings.CANNOT_BUY_PRODUCT_DUE_TO_AGE_RESTRICTIONS);
            } else {
                return true;
            }
        }

        return true;
    }

    @Override
    public double calculateCustomerProducts(List<Product> products) {
        double totalPrice = 0.0;

        for(Product product : products) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }

    @Override
    public PaymentMethod requestPaymentMethodFromCustomer(String option) {
        if(option.isEmpty()) {
            return requestPaymentMethodFromCustomer(option);
        } else {
            boolean isCash = option.equals("cash") || option.startsWith("c");

            if(isCash) {
                return PaymentMethod.CASH;
            } else {
                return PaymentMethod.ATM;
            }
        }
    }

    @Override
    public boolean verifyCash(double customerAmount, List<Product> products) throws PaymentException {
        double amount = calculateCustomerProducts(products);

        if(amount == customerAmount) {
            setTotalMoneyInStore(getTotalMoneyInStore() + amount);
            return true;
        } else {
            throw new PaymentException(ErrorStrings.CASH_PAYMENT_METHOD_FAILED);
        }
    }

    @Override
    public boolean verifyATM(PaymentResultEnum result,  List<Product> products) throws PaymentException{
        double amount = calculateCustomerProducts(products);

        if(result == PaymentResultEnum.PAID) {
            setTotalMoneyInStore(getTotalMoneyInStore() + amount);
            return true;
        } else {
            throw new PaymentException(ErrorStrings.BANK_PAYMENT_METHOD_FAILED);
        }
    }

    @Override
    public boolean errorInCheckout() {
        Commons.debugAction(
                false,
                "We couldn't finish this transaction, do you want to start again?. y/n? \n"
        );

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next().toLowerCase();

        return input.equals("yes") || input.startsWith("y");
    }

    @Override
    public Receipt dispenseReceipt(Customer customer) {
        UUID receiptId = IDGenerators.assignId();
        Calendar dateTime = Calendar.getInstance();

        return new Receipt(
                receiptId,
                dateTime.getTime(),
                customer
        );
    }

    /**
     * This finishes the checkout from the Customer's cart
     *
     * @return Authentication as successful or not
     */
    @Override
    public AuthenticationTypes finishCheckout() {
        return null;
    }
}
