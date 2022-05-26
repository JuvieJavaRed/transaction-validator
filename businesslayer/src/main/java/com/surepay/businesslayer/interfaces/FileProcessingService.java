package com.surepay.businesslayer.interfaces;

import com.surepay.businesslayer.exceptions.TransactionValidationException;
import com.surepay.datalayer.entities.Transactions;

import java.util.List;

/**
 * This interface handles methods for all File level processing
 *
 * *@author Mthokozisi Nyoni
 */
public interface FileProcessingService {

    /**
     * Parse input file.
     *
     * @param filePath - provides the path of the files that needs to be parsed
     * @return List Transactions - returns the list of Transaction Records parsed from the file
     * @throws TransactionValidationException the bank transaction validation exception
     */
    List<Transactions> parseFile(String filePath) throws TransactionValidationException;
}
