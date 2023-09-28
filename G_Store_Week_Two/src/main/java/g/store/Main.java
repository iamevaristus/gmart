package g.store;

import g.store.enums.others.Gender;
import g.store.enums.roles.StaffRole;
import g.store.enums.transactions.PaymentMethod;
import g.store.exception.ErrorStrings;
import g.store.exception.StoreException;
import g.store.model.entities.Cashier;
import g.store.model.entities.Customer;
import g.store.model.entities.Manager;
import g.store.model.entities.Store;
import g.store.model.transactions.Product;
import g.store.model.transactions.Receipt;
import g.store.utils.CommonUtils;
import g.store.utils.CurrencyUtil;
import g.store.utils.IDGenerators;

import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static Store store = new Store();

    // Store new manager
    static Manager manager = new Manager(
            "Evaristus",
            "Mervo",
            "evaristusadimonyemma@gmail.com",
            IDGenerators.assignId(),
            StaffRole.MANAGER,
            Gender.MALE,
            30000
    );

    static {
        System.out.println(
                """
                Welcome to G-Store!
                The best convenience store ever!!!

                """
        );

        // Save the manager details to the Store
        store.setManager(manager);

        // Get Store List of Products from CSV
        try {
            store.getStoreProducts();
        } catch (StoreException e) {
            throw new RuntimeException(e);
        }

        // Manager should hire cashier
        Cashier cashier = manager.hireCashier(
                "Francis",
                "Michael",
                "francismichael@gmail.com",
                Gender.FEMALE
        );
        store.setCashier(cashier);
    }

    public static void main(String[] args) {
        // Customer List of Product
        List<Product> customerCartProducts = new ArrayList<>();

        // Customer that is in the store
        Customer customer = new Customer(
                customerCartProducts,
                IDGenerators.assignId(),
                30000
        );

        Scanner scanner = new Scanner(System.in);

        // Start simulation
        CommonUtils.print("Welcome, these are the products in G-Store.\n");
        for(Product product : store.getProducts()) {
            CommonUtils.print(
                    "Name: " + product.getProductName() + "\n" +
                    "Price: " + CurrencyUtil.getLocalCurrencyFromDouble(product.getProductPrice()) + "\n"
            );
        }

        CommonUtils.print("For a smooth user experience in G-Store, please provide a name....\n");

        String customerName = scanner.nextLine().trim();

        if(customerName.isEmpty()) {
            customerName = "Customer";
            customer.setName(customerName);
        } else {
            customer.setName(customerName);
        }

        CommonUtils.print("""
            Enter the name of the product you want to buy and click enter.
            Please type 'done' when you are done picking products.
        """);
        boolean canFetchData = true;

        while(canFetchData) {
            String result = scanner.nextLine().trim().toLowerCase();

            if(result.equalsIgnoreCase("done")) {
                canFetchData = false;
            } else {
                Optional<Product> product = store.getProducts()
                        .stream()
                        .filter(prod -> prod.getProductName().toLowerCase().equals(result))
                        .findFirst();

                if(product.isPresent() && product.get().isOutOfStock()) {
                    CommonUtils.error(
                            "This product is " + ErrorStrings.OUT_OF_STOCK + ". " +
                                    "Pick another product or exit the store by typing 'done'\n"
                    );
                } else if(product.isPresent()) {
                    customerCartProducts.add(product.get());
                } else {
                    CommonUtils.error("""
                            Product is not in store. Please pick another, or exit store by typing 'done'
                    """);
                }
            }
        }

        finishTransaction(scanner, customer, customerCartProducts);
    }

    public static void finishTransaction(Scanner scanner, Customer customer, List<Product> customerCartProducts) {
        if(customer.proceedToCheckout()) {
            // Calculate total of product amount in customer cart product
            double totalAmount = store.getCashier().calculateCustomerProducts(customerCartProducts);

            // Give amount feedback to customer
            CommonUtils.print(
                    "The total amount for what you want to buy is: "
                            + CurrencyUtil.getLocalCurrencyFromDouble(totalAmount)
                            + "\n"
            );

            // Get payment method from customer.
            CommonUtils.print("Pick a payment method: ATM/CASH?\n");
            CommonUtils.error(
                    "NOTE: If you type something else not ATM/CASH, it will automatically pick ATM for you!\n"
            );

            // Payment Method
            PaymentMethod payMethod;

            // Cash
            double cash = 0;

            // Change
            double change = 0;

            String paymentMethod = scanner.nextLine().trim().toLowerCase();
            if(paymentMethod.contains("cash") || paymentMethod.startsWith("c")) {
                // Cash payment procedure
                CommonUtils.print("Please input your cash amount...\n");
                payMethod = PaymentMethod.CASH;

                // Get cash input and calculate
                boolean stillFetchingAmount = true;
                String cashString = "";

                while(stillFetchingAmount) {
                    cashString = scanner.nextLine().trim().toLowerCase();

                    if(cashString.isEmpty()) {
                        CommonUtils.error(ErrorStrings.CASH_EMPTY);
                    } else {
                        try {
                            cash = Double.parseDouble(cashString);

                            if (cash >= totalAmount) {
                                // Continue transaction
                                change = cash > totalAmount
                                        ? cash - totalAmount
                                        : 0;
                                stillFetchingAmount = false;
                            } else {
                                // Give feedback to the customer
                                CommonUtils.error(
                                        "Amount is not equal to total amount...\n" +
                                                "Total is " + totalAmount +
                                                "\n" +
                                                "Input amount that equals your total amount."
                                );
                            }
                        } catch (NumberFormatException e) {
                            CommonUtils.error("Please input only numbers.");
                        }
                    }
                }

                finishCheckout(scanner, customer, customerCartProducts, payMethod, cash, change);
            } else {
                // ATM payment procedure
                CommonUtils.print("How much do you have in your bank account?...\n");
                payMethod = PaymentMethod.ATM;

                // Get cash input and calculate
                boolean stillFetchingAmount = true;
                String cashString = "";

                while(stillFetchingAmount) {
                    cashString = scanner.nextLine().trim().toLowerCase();

                    if(cashString.isEmpty()) {
                        CommonUtils.error(ErrorStrings.CASH_EMPTY);
                    } else {
                        try {
                            cash = Double.parseDouble(cashString);

                            if (cash >= totalAmount) {
                                change = cash > totalAmount
                                        ? cash - totalAmount
                                        : 0;
                                cash = totalAmount;
                                stillFetchingAmount = false;
                            } else {
                                // Give feedback to the customer
                                CommonUtils.error(
                                        "You don't have enough funds to complete this transaction.\n" +
                                                "Total is " + totalAmount +
                                                "\n" +
                                                "Want to try again or end transaction? Y/N...."
                                );

                                String input = scanner.nextLine().trim().toLowerCase();
                                if(input.equals("true") || input.startsWith("t")
                                        || input.startsWith("y") || input.equals("yes")
                                ) {
                                    finishTransaction(scanner, customer, customerCartProducts);
                                } else {
                                    scanner.close();
                                    break;
                                }
                            }
                        } catch (NumberFormatException e) {
                            CommonUtils.error("Please input only numbers.");
                        }
                    }
                }

                finishCheckout(scanner, customer, customerCartProducts, payMethod, cash, change);
            }
            scanner.close();
        } else {
            // End simulation
            CommonUtils.print("Thanks for shopping at G-Store. Hoping to see you soon.");
        }
    }

    private static void finishCheckout(
            Scanner scanner,
            Customer customer,
            List<Product> customerCartProducts,
            PaymentMethod payMethod,
            double cash,
            double change
    ) {
        try {
            if(store.getCashier().updateStoreData(cash, customerCartProducts)) {
                customer.setChange(change);
                Receipt receipt = store.getCashier().dispenseReceipt(customer, payMethod);
                CommonUtils.print(
                        "Hey " + customer.getName() + "\n" +
                        "Your transaction was successful, please type 'Collect' to collect your receipt.\n"
                );

                String collect = scanner.nextLine().trim().toLowerCase();
                customer.collectReceipt(receipt);
                CommonUtils.print(
                        "Hey " + customer.getName() + "\n" +
                        "Thanks for your time in G-Store. Hoping to see you another time."
                );
                scanner.close();
            } else {
                restartOrEndTransaction(scanner, customer, customerCartProducts);
            }
        } catch (Exception e) {
            CommonUtils.error(e.toString());
            restartOrEndTransaction(scanner, customer, customerCartProducts);
        }
    }

    public static void restartOrEndTransaction(
            Scanner scanner,
            Customer customer,
            List<Product> customerCartProducts
    ) {
        CommonUtils.error("We couldn't finish this transaction, do you want to start again?. y/n? \n");

        String input = scanner.nextLine().trim().toLowerCase();
        if(input.equals("true") || input.startsWith("t")
                || input.startsWith("y") || input.equals("yes")
        ) {
            finishTransaction(scanner, customer, customerCartProducts);
        } else {
            scanner.close();
        }
    }
}