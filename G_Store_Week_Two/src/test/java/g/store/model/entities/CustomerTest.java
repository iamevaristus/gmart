package g.store.model.entities;

import g.store.enums.transactions.PaymentResult;
import g.store.enums.transactions.ProductStatus;
import g.store.exception.CartException;
import g.store.exception.PaymentException;
import g.store.exception.StoreException;
import g.store.model.transactions.Product;
import g.store.utils.IDGenerators;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    Store store = new Store();

    Product product = new Product(
            "Alcohol",
            15000,
            1
    );

    Product outOfStockProduct = new Product(ProductStatus.OUT_OF_STOCK);

    List<Product> customerProducts = new ArrayList<>();

    Customer customer = new Customer(
            customerProducts,
            IDGenerators.assignId(),
            0.0
    );

    @Test
    void shouldShowTrueThatCustomerCartListOfProductIsNotEmptyWhenCustomerAddsProductToCart() {
        try {
            boolean notEmpty = !customer.addToCart(product).getProducts().isEmpty();
            assertTrue(notEmpty);
        } catch (CartException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldThrowCartExceptionIfProductIsOutOfStockWhenAddingProductToCustomersExistingCart() {
        assertThrows(CartException.class, () -> {
            customer.addToCart(outOfStockProduct);
        });
    }

    @Test
    void shouldRemoveProductFromCustomersExistingCart() {
        try {
            customer.addToCart(product);
            assertTrue(customer.removeFromCart(product).getProducts().isEmpty());
        } catch (CartException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldThrowCartExceptionIfProductIsNotInCustomerProductListWhenRemovingProductFromCart() {
        assertThrows(CartException.class, () -> {
            customer.removeFromCart(product);
        });
    }

    @Test
    void shouldReturnTrueIfTheCustomerHasAnyProductToCheckoutFromTheCart() {
        try {
            customer.addToCart(product);
        } catch (CartException e) {
            throw new RuntimeException(e);
        }
        assertTrue(customer.proceedToCheckout());
    }

    @Test
    void shouldReturnFalseIfTheCustomerHasNoProductToCheckoutFromTheCart() {
        assertFalse(customer.proceedToCheckout());
    }

    @Test
    void shouldReturn5000FromTheCustomerInput() {
        try {
            assertEquals("5000", customer.pay("5000"));
        } catch (PaymentException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldThrowPaymentExceptionWhenCustomerHasNoAmountInput() {
        assertThrows(PaymentException.class, () -> {
            customer.pay("");
        });
    }
}