package g.store.model.entities;

import g.store.enums.auth.ActionStatus;
import g.store.enums.transactions.ProductStatus;
import g.store.exception.ErrorStrings;
import g.store.exception.StoreException;
import g.store.model.transactions.Product;
import g.store.services.StoreService;
import g.store.enums.roles.StaffRole;
import g.store.utils.CommonUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Store implements StoreService {
    /*
    G-Store Manager
     */
    private Manager manager;

    /*
    G-Store Cashier
     */
    private Cashier cashier;

    /*
    G-Store Total Income
     */
    private double totalMoneyInStore;

//    /*
//    List of feedback received about the store and its body.
//     */
//    private List<Feedback> feedbackList = new ArrayList<>();

    /**
     * List of Products in the Store
     */
    private List<Product> products = new ArrayList<>();

    /**
     * Get the list of products in the store.
     * @return List of Products.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets Products in the list with the new List of products
     * @param products List of Products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

//    /**
//     * Check if there is a cashier in the store.
//     * @return Boolean <Supplier></Supplier>
//     */
//    public Supplier<Boolean> getStoreHasCashier() {
//        return storeHasCashier;
//    }

//    /**
//     * Checks if the store has a manager.
//     * @return Boolean <Supplier></Supplier>
//     */
//    public Supplier<Boolean> getStoreHasManager() {
//        return storeHasManager;
//    }

    // The header of the data from CSV
    private String dataHeader;

    public Store() {}

    public Store(
            Manager manager,
            Cashier cashier,
            double storeIncome
    ) {
        this.manager = manager;
        this.cashier = cashier;
        this.totalMoneyInStore = storeIncome;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

//    public List<Feedback> getFeedbackList() {
//        return feedbackList;
//    }
//
//    public void setFeedbackList(List<Feedback> feedbackList) {
//        this.feedbackList = feedbackList;
//    }

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public double getTotalMoneyInStore() {
        return totalMoneyInStore;
    }

    public void setTotalMoneyInStore(double totalMoneyInStore) {
        this.totalMoneyInStore = totalMoneyInStore;
    }

    /**
     * This checks if the store has a cashier or not.
     */
    private final Supplier<Boolean> storeHasCashier = () -> {
        boolean isNull = cashier == null;
        return !isNull && !cashier.getFullName().isEmpty() && this.cashier.getPosition() == StaffRole.CASHIER;
    };

    /**
     * Checks if the store has a cashier.
     * @return Returns true, if yes.
     */
    public boolean hasCashier() {
        return storeHasCashier.get();
    }

    /**
     * This checks if the store has a manager or not.
     */
    private final Supplier<Boolean> storeHasManager = () -> {
        boolean isNull = manager == null;
        return !isNull && !manager.getFullName().isEmpty() && this.manager.getPosition() == StaffRole.MANAGER;
    };

    /**
     * Checks if the store has a manager or not.
     * @return Returns true, if yes.
     */
    public boolean hasManager() {
        return storeHasManager.get();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public List<Product> getStoreProducts() throws StoreException {
        // New List
        List<Product> newList = new ArrayList<>();

        try(BufferedReader reader = Files.newBufferedReader(CommonUtils.productPath)){
            String line;

            // This is to get the header of the csv file
            boolean isHeader = true;

            while((line = reader.readLine()) != null) {
                // Read header and skip to the data part
                if(isHeader) {
                    dataHeader = line;
                    isHeader = false;
                    continue;
                }
                Product product = new Product();
                product.getProductFromCsvData(line.split(","));
                newList.add(product);
                setProducts(newList);
            }
            return this.products;
        } catch (Exception e) {
            throw new StoreException(e.toString());
        }
    }

    @Override
    public ActionStatus updateProductInStoreListOfProducts(Product product) throws StoreException {
        try {
            List<Product> existingList = getStoreProducts();
            int index = -1; // Initialize index to -1

            // Find the index of the product in the list based on productId
            for (int i = 0; i < existingList.size(); i++) {
                if (existingList.get(i).getProductId().equals(product.getProductId())) {
                    index = i;
                    break; // Exit the loop when a match is found
                }
            }

            if (index != -1) {
                int remaining = product.getProductQuantity() - 1;

                if (remaining == 0) {
                    product.setProductStatus(ProductStatus.OUT_OF_STOCK);
                }

                // Update product quantity
                product.setProductQuantity(remaining);
                existingList.set(index, product);
                setProducts(existingList);
                return ActionStatus.SUCCESSFUL;
            } else {
                return ActionStatus.ERROR;
            }
        } catch (StoreException e) {
            throw new StoreException(e.toString());
        }
    }


    @Override
    public ActionStatus updateListOfProductsInStoreCSVData() throws StoreException {
        var existingList = getProducts();

        try(BufferedWriter writer = Files.newBufferedWriter(CommonUtils.productPath)) {
            writer.write(dataHeader);
            writer.newLine();

            // Write product data to CSV
            for(Product product : existingList) {
                writer.write(product.toDataString());
            }
            return ActionStatus.SUCCESSFUL;
        } catch (Exception e) {
            // If an exception occurs, throw a StoreException
            throw new StoreException(e.toString());
        }
    }

    @Override
    public ActionStatus updateData(List<Product> products) throws StoreException {
        ActionStatus action = null;
        for(Product p : products) {
            action = updateProductInStoreListOfProducts(p);
        }

        if(action == ActionStatus.SUCCESSFUL) {
            return updateListOfProductsInStoreCSVData();
        }

        throw new StoreException(ErrorStrings.STORE_EXCEPTION);
    }

    /**
     * This is a list of products a minor cannot buy.
     */
    public static String[] notForMinor = {
            "Alcohol",
            "Cigarette",
            "Condom",
    };
}
