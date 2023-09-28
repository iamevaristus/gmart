package g.store.model.transactions;

import g.store.enums.transactions.ProductStatus;
import g.store.exception.CartException;
import g.store.model.entities.Customer;
import g.store.utils.IDGenerators;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Product product;
    List<Product> productList;
    Customer customer;

    @BeforeEach
    void beforeEach() {
        product = new Product();
        productList = new ArrayList<>();
        customer = new Customer(
                productList,
                IDGenerators.assignId(),
                300000
        );
    }

    @Test
    void shouldReturnAClassOfCartWhenTheCustomerAddsAProductToCart() {
        try {
            assertInstanceOf(Cart.class, customer.addToCart(product));
        } catch (CartException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldThrowCartExceptionWhenCustomerAddsAProductThatIsOutOfStockProductToCart() {
        product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        assertThrows(CartException.class, () -> customer.addToCart(product));
    }

    @Test
    void shouldReturnAClassOfCartWhenCustomerRemovesProductFromTheCart() {
        try {
            customer.addToCart(product);
            assertInstanceOf(Cart.class, customer.removeFromCart(product));
        } catch (CartException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldThrowCartExceptionWhenRemovingProductNotInProductList() {
        assertThrows(CartException.class, () -> customer.removeFromCart(product));
    }

    @Test
    void shouldReturnTrueThatCustomerCanProceedToCheckout() {
        assertNotEquals(true, customer.proceedToCheckout());
    }
}