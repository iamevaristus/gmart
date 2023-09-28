package g.store.model.transactions;

import java.util.List;

/**
 * This class is a wrapper for the different categories of products that will be in the G-Store.
 *
 * It will contain different fields spreading across each sub product category.
 * It is like Laptop, Car, Beverages, etc
 */
public class ProductFamily {
    /**
     * The ID of the product category
     */
    private String categoryId;

    /**
     * The Name of the product category. Example: Car, Pen, Laptop
     */
    private String categoryName;

    /**
     * This is the list of product parents in the category
     */
    private List<ProductParent> productParents;

    public ProductFamily(
            String categoryId,
            String categoryName,
            List<ProductParent> productParents
    ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productParents = productParents;
    }

    public ProductFamily(
            ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
