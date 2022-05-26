package com.surepay.businesslayer.interfacesimpl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.surepay.businesslayer.exceptions.TransactionValidationException;
import com.surepay.businesslayer.interfaces.FileProcessingService;
import com.surepay.datalayer.entities.Transactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Component("csvProcessingService")
@Slf4j
public class CsvFileProcessingServiceImpl implements FileProcessingService {
    /**
     * Parse input file.
     *
     * @param filePath - provides the path of the files that needs to be parsed
     * @return List Transactions - returns the list of Transaction Records parsed from the file
     * @throws TransactionValidationException the bank transaction validation exception
     */
    @Override
    public List<Transactions> parseFile(String filePath) throws TransactionValidationException {
        try {
            Reader reader = new BufferedReader(new FileReader(new File(filePath)));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Transactions.class)
                    .withSeparator(',')
                    .withIgnoreEmptyLine(true)
                    .build();

            return csvToBean.parse();

        } catch (NumberFormatException numberFormatException) {
            log.error("Error in reading the CSV file, data format issue.", numberFormatException);
            throw new TransactionValidationException
                    ("Error in reading the CSV file, data format issue." + numberFormatException.getMessage());
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("File was not found from specified path", fileNotFoundException);
            throw new TransactionValidationException("File was not found from specified path" + fileNotFoundException.getMessage());
        }
    }
}
