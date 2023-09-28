package g.store.services;

import g.store.exception.CartException;
import g.store.exception.PaymentException;
import g.store.manager.IDGenerators;
import g.store.model.entities.Cashier;
import g.store.model.entities.Customer;
import g.store.model.transactions.Product;
import g.store.model.transactions.Receipt;
import g.store.types.GenderTypes;
import g.store.types.payment_types.PaymentMethod;
import g.store.types.payment_types.PaymentResultEnum;
import g.store.types.staff_roles.StaffAuthority;
import g.store.types.staff_roles.StaffPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierServiceTest {
    Product product = new Product(
        "Alcohol",
        15000,
        1
    );

    List<Product> productList = new ArrayList<Product>(){{
        add(product);
    }};

    Cashier cashier = new Cashier(
        "Evar",
        "Frank",
        "eva@dasd.com",
        null,
        StaffPosition.CASHIER,
        StaffAuthority.SELL,
        GenderTypes.CANT_SAY,
        10000
    );

    Customer customer = new Customer(
            productList,
            IDGenerators.assignId(),
            0.0
    );

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void throwCartExceptionWhenCustomerIsLessThan18ToBuyAlcohol() {
        assertThrows(CartException.class, () -> {
            cashier.verifyCustomer(product, 17);
        });
    }

    @Test
    void showTrueWhenACustomerCanBuyProduct() throws CartException {
        assertTrue(cashier.verifyCustomer(product, 24));
    }

    @Test
    void show15000AsTheTotalAmountOfProductsInProductList() {
        assertEquals(15000, cashier.calculateCustomerProducts(productList));
    }

    @Test
    void showThatTheTotalAmountOfProductsInTheProductListIsNotEqualsTo1000() {
        assertNotEquals(1000, cashier.calculateCustomerProducts(productList));
    }

    @Test
    void shouldShowThatTheUserPickedATMPaymentMethod() {
        assertEquals(PaymentMethod.ATM, cashier.requestPaymentMethodFromCustomer("ATM"));
    }

    @Test
    void shouldShowThatTheUserDidntPickedCashPaymentMethod() {
        assertNotEquals(PaymentMethod.CASH, cashier.requestPaymentMethodFromCustomer("a"));
    }

    @Test
    void shouldShowThatTotalAmountOfCustomerProductWhichIs15000IsSameAsAmountGivenByCustomer() throws PaymentException {
        assertTrue(cashier.verifyCash(15000, productList));
    }

    @Test
    void shouldThrowPaymentExceptionWhenTotalAmountOfCustomerProductWhichIs15000IsNotEqualToWhatCustomerGave() {
        assertThrows(PaymentException.class, () -> {
            cashier.verifyCash(1500, productList);
        });
    }

    @Test
    void shouldShowThatCustomerPaidWithATM() throws PaymentException {
        assertTrue(cashier.verifyATM(PaymentResultEnum.PAID, productList));
    }

    @Test
    void shouldThrowPaymentExceptionWhenCustomerATMTransactionIsNotSuccessful() {
        assertThrows(PaymentException.class, () -> {
            cashier.verifyATM(PaymentResultEnum.NOT_PAID, productList);
        });
    }

    @Test
    void errorInCheckout() {
    }

    @Test
    void shouldShowADispensedReceipt() {
        Receipt receipt = cashier.dispenseReceipt(customer);
        assertEquals(customer.getCustomerId(), receipt.getCustomer().getCustomerId());
    }

    @Test
    void finishCheckout() {
    }
}