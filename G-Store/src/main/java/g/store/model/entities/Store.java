package g.store.model.entities;

import g.store.services.StoreService;
import g.store.model.others.Feedback;
import g.store.types.staff_roles.StaffPosition;

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

    /*
    List of feedback received about the store and its body.
     */
    private List<Feedback> feedbackList;

    public Store() {}

    public Store(
            Manager manager,
            Cashier cashier,
            double totalMoneyInStore,
            List<Feedback> feedbackList
    ) {
        this.manager = manager;
        this.cashier = cashier;
        this.totalMoneyInStore = totalMoneyInStore;
        this.feedbackList = feedbackList;
    }

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

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

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
        return !cashier.getFullName().isEmpty() && this.cashier.getPosition() == StaffPosition.CASHIER;
    };

    public boolean hasCashier() {
        return storeHasCashier.get();
    }

    /**
     * This checks if the store has a manager or not.
     */
    private final Supplier<Boolean> storeHasManager = () -> {
        return !manager.getFullName().isEmpty() && this.manager.getPosition() == StaffPosition.MANAGER;
    };

    public boolean hasManager() {
        return storeHasManager.get();
    }

    public void addFeedback(Feedback feedback) {
        var existingList = this.getFeedbackList();

        existingList.add(feedback);

        this.setFeedbackList(existingList);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * This gets the details of the Store and prints it as a String.
     *
     * @return String
     */
    @Override
    public String toStoreString() {
        return null;
    }

    public static String[] notForMinor = {
            "Alcohol",
            "Cigarette",
            "Condom",
    };
}
