package g.store.services;

import g.store.exception.PaymentException;
import g.store.model.entities.Cashier;
import g.store.enums.others.Gender;
import g.store.enums.transactions.PaymentResult;

public interface ManagerService {

    /**
     * This will enable the manager to hire a cashier.
     * @param firstName First name of the hiring cashier.
     * @param lastName Last name of the hiring cashier.
     * @param emailAddress Email address of the hiring cashier.
     * @param gender Gender of the hiring cashier
     * @return Cashier that is hired.
     */
    Cashier hireCashier(
            String firstName,
            String lastName,
            String emailAddress,
            Gender gender
    );

//    /**
//     * This will enable the manager to fire/terminate a cashier.
//     * @param cashier The cashier to be fired/terminated.
//     */
//    void fireCashier(Cashier cashier);
//
//    /**
//     * This will enable the manager to pay salary to the cashier
//     * @param cashier The Cashier to be paid
//     * @return Whether PAID or Not with PaymentResultEnum
//     */
//    PaymentResult paySalary(Cashier cashier) throws PaymentException;
}
