package com.surepay.datalayer.services;

import com.surepay.datalayer.entities.Transactions;
import java.util.List;

public interface TransactionsRepositoryService {
    public void saveTransactions(List<Transactions> transactionsList);
}
