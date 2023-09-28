package g.store;

import g.store.manager.IDGenerators;
import g.store.model.entities.Cashier;
import g.store.model.entities.Customer;
import g.store.model.transactions.Product;
import g.store.types.GenderTypes;
import g.store.types.staff_roles.StaffAuthority;
import g.store.types.staff_roles.StaffPosition;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Product product = new Product(
                "Pen",
                10.0,
                1
        );

        Product product2 = new Product(
                "Pen",
                1500000.0,
                1
        );

        Product product3 = new Product(
                "Biro",
                500000.0,
                1
        );

        List<Product> products = new LinkedList<Product>(){{
            add(product);
            add(product2);
            add(product3);
        }};

        UUID customerId = IDGenerators.assignId();

        Customer customer = new Customer(
                products,
                customerId,
                10.0
        );

        Cashier cashier = new Cashier(
                "Francis",
                "Frank",
                "",
                IDGenerators.assignId(),
                StaffPosition.NONE,
                StaffAuthority.SELL,
                GenderTypes.NONE,
                0
        );

//        System.out.println(customer.addToCart(product));
        System.out.println(cashier.dispenseReceipt(customer));
    }
}