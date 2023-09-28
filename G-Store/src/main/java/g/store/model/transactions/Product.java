package g.store.model.transactions;

import g.store.manager.CurrencyManager;
import g.store.manager.IDGenerators;

/**
 * This is a class of Product for G-Store. it contains the following parameters:
 * UUID productId,
 * String productName,
 * String price,
 * Integer quantity
 *
 * @author Evaristus Adimonyemma
 */
public class Product {

    /// PRODUCT ID
    private String productId;

    /// PRODUCT NAME
    private String productName;

    /// PRODUCT PRICE
    private double price;

    /// PRODUCT QUANTITY
    private int quantity;

    /// Product Constructor
    public Product(
            String productName,
            double price,
            int quantity
    ) {
        this.productId = IDGenerators.assignProductId();
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        String header = "The information for this product is as follows: \n";

        String productInfo =
                "ID: " + productId + "\n" +
                "Name of Product: " + productName + "\n" +
                "Price of Product: " + CurrencyManager.getLocalCurrencyFromDouble(price) + "\n"
                ;
        return header + productInfo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
