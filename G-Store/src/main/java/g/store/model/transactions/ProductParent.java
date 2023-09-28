package g.store.model.transactions;

import java.util.List;

/**
 * This is a wrapper for the parent of the Product Family
 * Example: If the product family is named Laptop, the product parent will then be
 * different brand names for the product itself.
 * So, if laptop is the product family, then product parent will be names like
 * hp, apple, acer, etc
 */
public class ProductParent extends ProductFamily {

    /**
     * This is the parent ID
     */
    private String parentId;

    /**
     * This is the parent name
     */
    private String parentName;

    /**
     * This is the list of products in the parent
     */
    private List<Product> productList;


    public ProductParent(
            String parentId,
            String parentName
    ) {
        this();
        this.parentId = parentId;
        this.parentName = parentName;
    }

    public ProductParent(
            String parentId,
            String parentName,
            List<Product> productList
    ) {
        this();
        this.parentId = parentId;
        this.parentName = parentName;
        this.productList = productList;
    }

    public ProductParent() {
        super();
    }
}
