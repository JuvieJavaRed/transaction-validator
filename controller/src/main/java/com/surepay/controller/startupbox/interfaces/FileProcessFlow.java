package com.surepay.controller.startupbox.interfaces;

import com.surepay.businesslayer.exceptions.TransactionValidationException;

public interface FileProcessFlow {
    /**
     * method to process input file and create output file
     *
     * @param inputFilePath  the input file path
     * @throws TransactionValidationException the bank transaction validation exception
     */
    void processTransactionRecordFile(final String inputFilePath) throws TransactionValidationException;
}
