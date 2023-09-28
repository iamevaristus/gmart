package g.store.services;

import g.store.model.transactions.Receipt;

public interface CustomerService {
    public void collectReceipt(Receipt receipt);
}
