package g.store.model.transactions;

import g.store.enums.transactions.ProductStatus;
import g.store.utils.CurrencyUtil;
import g.store.utils.IDGenerators;

/**
 * This is a class of Product for G-Store. it contains the following parameters:
 * UUID productId,
 * String productName,
 * String productPrice,
 * Integer productQuantity
 *
 * @author Evaristus Adimonyemma
 */
public class Product {
//
//    private static final long serialVersionUID = 1L;

    /// PRODUCT ID
    private String productId;

    /// PRODUCT NAME
    private String productName;

    /// PRODUCT DESCRIPTION
    private String productDescription;

    /// PRODUCT IMAGE
    private String productImage;

    /// PRODUCT PRICE
    private double productPrice;

    /// PRODUCT QUANTITY
    private int productQuantity;

    /// PRODUCT CATEGORY
    private ProductCategory productCategory;

    /// PRODUCT STATUS
    private ProductStatus productStatus;

    public Product(
            String productId,
            String productName,
            String productDescription,
            String productImage,
            double productPrice,
            int productQuantity,
            ProductCategory productCategory,
            ProductStatus productStatus
    ) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCategory = productCategory;
        this.productStatus = productStatus;
    }

    /// Product Constructor
    public Product(
            String productName,
            double productPrice,
            int productQuantity
    ) {
        this.productId = IDGenerators.assignProductId();
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public Product(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public Product() {}

    /**
     * Get the product ID
     * @return String
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Set the product ID
     * @param productId String
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * Gets the product image.
     * @return The image of the product.
     */
    public String getProductImage() {
        return productImage;
    }

    /**
     * Sets the product image with the new image.
     * @param productImage The new product image.
     */
    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    /**
     * Gets the price of the product.
     * @return The price as double.
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the price of the product with the new price.
     * @param productPrice The new price of product.
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * The quantity of the product.
     * @return The Integer
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * Sets the quantity of the product with the new one.
     * @param productQuantity The int to be set
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Gets the category of the product.
     * @return ProductCategory
     */
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    /**
     * Sets the category of the product with the new one.
     * @param productCategory ProductCategory to be set
     */
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * Gets the status of the product.
     * @return ProductStatus
     */
    public ProductStatus getProductStatus() {
        return productStatus;
    }

    /**
     * Sets the status of the product with the new one.
     * @param productStatus ProductStatus to be set.
     */
    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * Get the product name
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Set the product name
     * @param productName Name of the product
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the product description
     * @return String
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the product description
     * @param productDescription Description of product
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Override
    public String toString() {
        String header = "The information for this product is as follows: \n";

        String productInfo =
                "ID: " + productId + "\n" +
                "Name of Product: " + productName + "\n" +
                "ProductName: " + productName + '\n' +
                "ProductDescription: " + productDescription + '\n' +
                "ProductImage: " + productImage + '\n' +
                "ProductQuantity: " + productQuantity + '\n' +
                "ProductCategory: " + productCategory.toString() + '\n' +
                "ProductStatus: " + productStatus + '\n' +
                "Price of Product: " + CurrencyUtil.getLocalCurrencyFromDouble(productPrice) + "\n"
                ;
        return header + productInfo;
    }

    public String toDataString() {
        String separator = ", ";

        return getProductCategory().getCategoryId() + separator +
                getProductCategory().getCategoryName() + separator +
                getProductId() + separator +
                getProductName() + separator +
                getProductDescription() + separator +
                getProductImage() + separator +
                getProductPrice() + separator +
                getProductQuantity() + separator +
                getProductStatus() + '\n';
    }

    public void getProductFromCsvData(String[] dataArray) {
        // Get the Product Category
        ProductCategory productCategory = new ProductCategory(
                dataArray[0].trim(),
                dataArray[1].trim()
        );

        this.setProductId(dataArray[2].trim()); // ProductId
        this.setProductName(dataArray[3].trim()); // ProductName
        this.setProductDescription(dataArray[4].trim()); // ProductDescription
        this.setProductImage(dataArray[5].trim()); // ProductImage
        this.setProductPrice(Double.parseDouble(dataArray[6].trim())); // ProductPrice
        this.setProductQuantity(Integer.parseInt(dataArray[7].trim())); // ProductQuantity
        this.setProductStatus(ProductStatus.valueOf(dataArray[8].trim())); // ProductStatus
        this.setProductCategory(productCategory); // ProductCategory
    }

    /**
     * Checks if the product is out of stock.
     * @return True or False
     */
    public boolean isOutOfStock() {
        return this.productStatus == ProductStatus.OUT_OF_STOCK;
    }
}
