package g.store.model.entities;

import g.store.enums.auth.ActionStatus;
import g.store.enums.others.Gender;
import g.store.enums.roles.StaffRole;
import g.store.enums.transactions.PaymentMethod;
import g.store.exception.CartException;
import g.store.model.transactions.Product;
import g.store.model.transactions.Receipt;
import g.store.utils.IDGenerators;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {

    Product product;
    List<Product> productList;
    Cashier cashier;
    Customer customer;

    @BeforeEach
    void before() {
        product = new Product(
                "Alcohol",
                15000,
                1
        );

        productList = new ArrayList<Product>(){{
            add(product);
        }};

        cashier = new Cashier(
                "Evar",
                "Frank",
                "eva@dasd.com",
                null,
                StaffRole.CASHIER,
                Gender.CANT_SAY,
                10000
        );

        customer = new Customer(
                productList,
                IDGenerators.assignId(),
                0.0
        );
    }

    @Test
    void shouldThrowCartExceptionWhenCustomerIsLessThan18ToBuyProductsNotMeantForMinors() {
        assertThrows(CartException.class, () -> {
            cashier.verifyIfCustomerIsEligibleToBuyProduct(
                    product,
                    17
            );
        });
    }

    @Test
    void shouldShowTrueThatACustomerIsEligibleToBuyProduct() throws CartException {
        assertTrue(cashier.verifyIfCustomerIsEligibleToBuyProduct(
                product,
                24
        ));
    }

    @Test
    void shouldShow15000AsTheTotalAmountOfProductsInCustomerProductList() {
        assertEquals(15000, cashier.calculateCustomerProducts(
                productList
        ));
    }

    @Test
    void shouldShowTotalNotEqualTo1000() {
        assertNotEquals(1000, cashier.calculateCustomerProducts(
                productList
        ));
    }

    @Test
    void shouldShowThatTheUserPickedATMPaymentMethodWhenTheCashierRequestedForIt() {
        assertEquals(PaymentMethod.ATM, cashier.requestPaymentMethodFromCustomer(
                "ATM"
        ));
    }

    @Test
    void shouldShowThatTheUserPickedATMButCashWasExpected() {
        assertNotEquals(PaymentMethod.CASH, cashier.requestPaymentMethodFromCustomer(
                "a"
        ));
    }

    @Test
    void shouldReturnAClassOfReceiptAfterDispensingReceipt() {
        assertInstanceOf(Receipt.class, cashier.dispenseReceipt(
                customer,
                PaymentMethod.ATM
        ));
    }
}