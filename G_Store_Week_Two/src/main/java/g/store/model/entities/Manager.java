package g.store.model.entities;

import g.store.exception.ErrorStrings;
import g.store.exception.PaymentException;
import g.store.services.ManagerService;
import g.store.utils.IDGenerators;
import g.store.enums.others.Gender;
import g.store.enums.transactions.PaymentResult;
import g.store.enums.roles.StaffRole;

import java.util.UUID;

public class Manager extends Staff implements ManagerService {
    public Manager(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffRole position,
            Gender gender,
            double salary
    ) {
        super(
                firstName,
                lastName,
                emailAddress,
                staffId,
                position,
                gender,
                salary
        );
    }

    @Override
    public Cashier hireCashier(
            String firstName,
            String lastName,
            String emailAddress,
            Gender gender
    ) {
        return new Cashier(
                firstName,
                lastName,
                emailAddress,
                IDGenerators.assignId(),
                StaffRole.CASHIER,
                gender,
                10000
        );
    }

//    @Override
//    public void fireCashier(Cashier cashier) {
//        Cashier newCashier = new Cashier(
//                "",
//                "",
//                "",
//                 null,
//                StaffRole.NONE,
//                Gender.NONE,
//                0
//        );
//        setCashier(newCashier);
//    }

//    @Override
//    public PaymentResult paySalary(Cashier cashier) throws PaymentException {
//        double cashierSalary = cashier.getSalary();
//
//        double moneyInStore = getTotalMoneyInStore();
//
//        if(moneyInStore < cashierSalary) {
//            throw new PaymentException(ErrorStrings.CANNOT_PAY_SALARY);
//        } else {
//            cashier.setSalary(cashierSalary + cashierSalary);
//            setCashier(cashier);
//            return PaymentResult.PAID;
//        }
//    }
}
