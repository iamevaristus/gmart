package g.store.model.entities;

import g.store.exception.PaymentException;
import g.store.services.ManagerService;
import g.store.manager.IDGenerators;
import g.store.types.GenderTypes;
import g.store.types.payment_types.PaymentResultEnum;
import g.store.types.staff_roles.StaffAuthority;
import g.store.types.staff_roles.StaffPosition;

import java.util.UUID;

public class Manager extends Staff implements ManagerService {
    public Manager(
            String firstName,
            String lastName,
            String emailAddress,
            UUID staffId,
            StaffPosition position,
            StaffAuthority authority,
            GenderTypes genderTypes,
            double salary
    ) {
        super(
                firstName,
                lastName,
                emailAddress,
                staffId,
                position,
                authority,
                genderTypes,
                salary
        );
    }

    @Override
    public Cashier hireCashier(
            String firstName,
            String lastName,
            String emailAddress,
            GenderTypes gender
    ) {
        Cashier newCashier = new Cashier(
                firstName,
                lastName,
                emailAddress,
                IDGenerators.assignId(),
                StaffPosition.CASHIER,
                StaffAuthority.SELL,
                gender,
                10000
        );

        newCashier.setStaff(newCashier);

        return newCashier;
    }

    @Override
    public void fireCashier(Cashier cashier) {
        Cashier newCashier = new Cashier(
                "",
                "",
                "",
                 null,
                StaffPosition.NONE,
                StaffAuthority.NONE,
                GenderTypes.NONE,
                0
        );

        newCashier.setStaff(newCashier);
    }

    @Override
    public PaymentResultEnum paySalary(Cashier cashier) throws PaymentException {
        double cashierSalary = cashier.getSalary();

        double moneyInStore = getTotalMoneyInStore();

        if(moneyInStore < cashierSalary) {
            throw new PaymentException("G-Store cannot pay salary to cashier due to low income from store");
        } else {
            cashier.setSalary(cashierSalary + cashierSalary);
            setCashier(cashier);
            return PaymentResultEnum.PAID;
        }
    }
}
