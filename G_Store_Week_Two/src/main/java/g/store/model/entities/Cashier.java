package g.store.model.entities;

import g.store.enums.auth.ActionStatus;
import g.store.enums.others.Gender;
import g.store.exception.CartException;
import g.store.exception.ErrorStrings;
import g.store.exception.PaymentException;
import g.store.exception.StoreException;
import g.store.services.CashierService;
import g.store.utils.CommonUtils;
import g.store.utils.IDGenerators;
import g.store.model.transactions.Receipt;
import g.store.model.transactions.Product;
import g.store.enums.transactions.PaymentMethod;
import g.store.enums.transactions.PaymentResult;
import g.store.enums.roles.StaffRole;

import java.util.*;

public class Cashier extends Staff implements CashierService {

    public Cashier(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffRole position,
            Gender gender,
            double salary
    ) {
        super(
                firstName,
                lastName,
                emailAddress,
                staffId,
                position,
                gender,
                salary
        );
    }

    @Override
    public boolean verifyIfCustomerIsEligibleToBuyProduct(Product product, int age) throws CartException {
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
            totalPrice += product.getProductPrice();
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
    public boolean updateStoreData(double customerAmount, List<Product> products) throws PaymentException {
        setTotalMoneyInStore(getTotalMoneyInStore() + customerAmount);
        try {
            var action = updateData(products);
            return action == ActionStatus.SUCCESSFUL;
        } catch (StoreException e) {
            throw new PaymentException(ErrorStrings.CASH_PAYMENT_METHOD_FAILED);
        }
    }

    @Override
    public Receipt dispenseReceipt(Customer customer, PaymentMethod paymentMethod) {
        UUID receiptId = IDGenerators.assignId();
        Calendar dateTime = Calendar.getInstance();

        return new Receipt(
                receiptId,
                dateTime.getTime(),
                customer,
                paymentMethod
        );
    }
}
