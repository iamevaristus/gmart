package g.store.model.transactions;

import g.store.exception.CartException;
import g.store.exception.ErrorStrings;
import g.store.services.CartService;

import java.util.List;
import java.util.Objects;

public abstract class Cart implements CartService {
    /**
     * Total number of products in cart
     */
    private int numberOfProducts;

    /**
     * List of products in cart
     */
    List<Product> products;

    public Cart(
            List<Product> products
    ) {
        this.products = products;
    }

//    public void setNumberOfProducts(int numberOfProducts) {
//        this.numberOfProducts = numberOfProducts;
//    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public Cart addToCart(Product product) throws CartException {
        if(product.isOutOfStock()) {
            throw new CartException(ErrorStrings.OUT_OF_STOCK);
        } else {
            List<Product> existingList = getProducts();

            existingList.add(product);
            setProducts(existingList);

            return this;
        }
    }

    @Override
    public Cart removeFromCart(Product product) throws CartException {
        List<Product> existingList = getProducts();

        if(existingList.stream().anyMatch(prod -> Objects.equals(prod.getProductId(), product.getProductId()))) {
            existingList.remove(product);

            setProducts(existingList);
            return this;
        }

        throw new CartException(ErrorStrings.NO_PRODUCT_IN_CUSTOMER_PRODUCT_LIST);
    }

    @Override
    public boolean proceedToCheckout() {
        products.removeIf(Product::isOutOfStock);

        return !products.isEmpty();
    }

    public abstract String toStringWithCart();
}
