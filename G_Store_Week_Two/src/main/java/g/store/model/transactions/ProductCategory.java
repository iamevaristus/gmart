package g.store.model.transactions;

import java.util.List;

/**
 * This class is a wrapper for the different categories of a product family that will be in the G-Store.
 * Example: {
 *     If the product family is Car, the product category will then contain things like:
 *     1. Corolla
 *     2. Toyota, etc.
 * }
 */
public class ProductCategory {
    /**
     * The ID of the product category
     */
    private String categoryId;

    /**
     * The Name of the product category. Example: Toyota, Camry, etc.
     */
    private String categoryName;

    public ProductCategory(
            String categoryId,
            String categoryName
    ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

//    public ProductCategory() {}

    public String getCategoryId() {
        return categoryId;
    }

//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }

    public String getCategoryName() {
        return categoryName;
    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }

    @Override
    public String toString() {
        return "ProductCategory" + '\n' +
                "CategoryId: " + categoryId + '\n' +
                "CategoryName: " + categoryName + '\n';
    }
}
