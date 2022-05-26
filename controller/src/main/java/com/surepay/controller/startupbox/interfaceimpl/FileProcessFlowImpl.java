package com.surepay.controller.startupbox.interfaceimpl;

import com.surepay.businesslayer.exceptions.TransactionValidationException;
import com.surepay.businesslayer.interfaces.FileProcessingService;
import com.surepay.businesslayer.interfaces.ValidationService;
import com.surepay.controller.startupbox.interfaces.FileProcessFlow;
import com.surepay.datalayer.entities.Reports;
import com.surepay.datalayer.entities.Transactions;
import com.surepay.datalayer.services.ReportsRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FileProcessFlowImpl implements FileProcessFlow {

    private final ValidationService validationService;
    @Qualifier("jsonProcessingService")
    private final FileProcessingService jsonProcessingService;
    @Qualifier("csvProcessingService")
    private final FileProcessingService csvProcessingService;
    private final ReportsRepositoryService reportsRepositoryService;

    /**
     * Instantiates a new Bank transaction file processor.
     *
     * @param validationService         the validation service
     * @param jsonProcessingService the json file processing service
     * @param csvProcessingService  the csv file processing service
     * @param reportsRepositoryService the service to write failed transactions to the database
     */
    @Autowired
    public FileProcessFlowImpl(ValidationService validationService,
                                            FileProcessingService jsonProcessingService,
                                            FileProcessingService csvProcessingService, ReportsRepositoryService reportsRepositoryService) {
        this.validationService = validationService;
        this.jsonProcessingService = jsonProcessingService;
        this.csvProcessingService = csvProcessingService;
        this.reportsRepositoryService = reportsRepositoryService;
    }

    /**
     * method to process input file and create output file
     *
     * @param inputFilePath  the input file path
     * @throws TransactionValidationException the bank transaction validation exception
     */
    @Override
    public void processTransactionRecordFile(String inputFilePath) throws TransactionValidationException {
        List<Transactions> transactionRecords = convertFileToDataStructure(inputFilePath);

        List<Transactions> invalidRecords = validationService.validateTransactionRecords(transactionRecords);

        generateInvalidRecordsReport(invalidRecords);
    }

    /**
     * method to convert file to list....
     *
     * @param inputFilePath  the input file path
     */
    public List<Transactions> convertFileToDataStructure(final String inputFilePath){
        final String inputFileType = FilenameUtils.getExtension(inputFilePath);

        return switch (inputFileType) {
            case "csv" -> csvProcessingService.parseFile(inputFilePath);
            case "json" -> jsonProcessingService.parseFile(inputFilePath);
            default -> {
                log.error("Invalid File Type. File Should Either Be Format JSON OR CSV");
                throw new TransactionValidationException("Invalid File Type. File Should Either Be Format JSON OR CSV");
            }
        };

    }

    /**
     * @param invalidTransactions List of transactions.
     * @throws TransactionValidationException
     *
    */
    public void generateInvalidRecordsReport(List<Transactions> invalidTransactions){
        try{
            reportsRepositoryService.saveReportTransactions(convertTransactionsToReports(invalidTransactions));
        }catch (Exception ex){
            log.error("The invalid transactions were not saved");
            throw new TransactionValidationException("Invalid Transactions Not Saved :"+ex.getMessage());
        }
    }

    /**
     * method to process map a list of object Transactions to a list of object Reports
     *
     * @param invalidTransactions
     * @throws TransactionValidationException the bank transaction validation exception
     * @returns List object Reports
     */
    public List<Reports> convertTransactionsToReports(List<Transactions> invalidTransactions){
        List<Reports> reportsList = invalidTransactions.stream().map(rep -> new Reports(rep.getTransactionReference(), rep.getAccountNumber(), rep.getDescription(), rep.getStartBalance(), rep.getMutation())).collect(Collectors.toList());
        return reportsList;
    }
}
