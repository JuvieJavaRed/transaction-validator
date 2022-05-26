package com.surepay.datalayer.services;

import com.surepay.datalayer.entities.Transactions;
import com.surepay.datalayer.repository.TransactionsRepository;

import java.util.List;

public class TransactionsRepositoryServiceImpl implements TransactionsRepositoryService {
    private final TransactionsRepository transactionsRepository;

    public TransactionsRepositoryServiceImpl(TransactionsRepository transactionsRepository){
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public void saveTransactions(List<Transactions> transactionsList) {
        transactionsRepository.saveAll(transactionsList);
    }
}
