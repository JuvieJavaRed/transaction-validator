package com.surepay.businesslayer.interfaces;

import com.surepay.datalayer.entities.Transactions;

import java.util.List;

/**
 * This interface handles methods for all File level processing
 *
 * @author Mthokozisi Nyoni
 */
public interface ValidationService {
    /**
     * Validate transaction records list.
     *
     * @param transactionsList  - provides list of records that needs to be validated the transaction records
     * @return the list Transactions - returns list of complex object type Transactions
     */
    List<Transactions> validateTransactionRecords(List<Transactions> transactionsList);

}
