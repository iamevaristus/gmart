package g.store.services;

import g.store.enums.auth.ActionStatus;
import g.store.exception.StoreException;
import g.store.model.transactions.Product;

import java.util.List;

public interface StoreService {
//    /**
//     * This gets the details of the Store and prints it as a String.
//     * @return String
//     */
//    public String toStoreString();

    /**
     * This will be fetching the list of products from the .csv file always.
     * @return <Product>List</Product>
     */
    public List<Product> getStoreProducts() throws StoreException;

    /**
     * This will update the quantity of the product in the list of product in the store.
     * @param product Product to be removed
     * @return True of false
     */
    public ActionStatus updateProductInStoreListOfProducts(Product product) throws StoreException;

    /**
     * Update the CSV file of the G-Store
     * @return True or False
     * @throws StoreException error
     */
    public ActionStatus updateListOfProductsInStoreCSVData() throws StoreException;

    /**
     * This will run the methods (updateProductInStore and updateCsvData)
     * @param products List of products to update with
     * @return True or false
     * @throws StoreException error
     */
    public ActionStatus updateData(List<Product> products) throws StoreException;
}
