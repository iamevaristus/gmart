package g.store.model.transactions;

import java.util.List;

public abstract class Cart {
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
        this.numberOfProducts = getNumberOfProducts();
        this.products = products;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * This adds to the user's cart
     * @param product from Product model
     * @return Cart model
     */
    public Cart addToCart(Product product) {
        List<Product> existingList = getProducts();

        existingList.add(product);
        setProducts(existingList);

        return this;
    }

    /**
     * This removes a product from the existing Cart's list
     * @param product Need the product to be removed
     * @return Cart
     */
    public Cart removeFromCart(Product product) {
        List<Product> existingList = getProducts();

        existingList.remove(product);

        setProducts(existingList);
        return this;
    }

    /**
     * This calculates and gets the number of products in the cart
     * @return int as number
     */
    public int getNumberOfProducts() {
        /// TODO: Mathematical function to execute checker
        return numberOfProducts;
    }

    /**
     * This method is used to check if the Customer can proceed to checkout or not
     * @return True, if the cart is not empty or false, if empty
     */
    public boolean proceedToCheckout() {
        return !products.isEmpty();
    }

    public abstract String toStringWithCart();
}
