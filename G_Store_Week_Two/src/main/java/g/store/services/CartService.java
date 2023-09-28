package g.store.services;

import g.store.exception.CartException;
import g.store.model.transactions.Cart;
import g.store.model.transactions.Product;

public interface CartService {

    /**
     * This adds to the user's cart
     * @param product from Product model
     * @return Cart model
     */
    public Cart addToCart(Product product) throws CartException;

    /**
     * This removes a product from the existing Cart's list
     * @param product Need the product to be removed
     * @return Cart
     */
    public Cart removeFromCart(Product product) throws CartException;

    /**
     * This method is used to check if the Customer can proceed to checkout or not
     * @return True, if the cart is not empty or false, if empty
     */
    public boolean proceedToCheckout();
}
