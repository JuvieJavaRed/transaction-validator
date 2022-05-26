package com.surepay.businesslayer.exceptions;

/*  *Custom exception class for defining exception handling for Bank Transaction Validation Service
 *
 *@author Mthokozisi Nyoni
 */
public class TransactionValidationException extends RuntimeException{
    private String message;
    /*   *parameterized constructor
     *
     *@param message exception message
    */
    public TransactionValidationException(String message) {
        super(message);
    }
}
