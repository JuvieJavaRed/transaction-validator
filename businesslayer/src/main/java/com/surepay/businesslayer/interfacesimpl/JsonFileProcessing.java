package com.surepay.businesslayer.interfacesimpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surepay.businesslayer.exceptions.TransactionValidationException;
import com.surepay.businesslayer.interfaces.FileProcessingService;
import com.surepay.datalayer.entities.Transactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component("jsonProcessingService")
public class JsonFileProcessing implements FileProcessingService {

    private final ObjectMapper mapper = new ObjectMapper();

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
            return mapper.readValue(new File(filePath), new TypeReference<List<Transactions>>() {
            });
        } catch (JsonMappingException jsonMappingException) {
            log.error("Error while reading data from JSON file. One of the numeric field contains non-numeric data.", jsonMappingException);
            throw new TransactionValidationException(
                    ("Error while reading data from JSON file. One of the numeric field contains non-numeric data." + jsonMappingException.getMessage()));
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("The file was not found in the specified path given", fileNotFoundException);
            throw new TransactionValidationException(
                    ("The file was not found in the specified path given" + fileNotFoundException.getMessage()));
        } catch (IOException ioException) {
            log.error("Exception while parsing JSON file.", ioException);
            throw new TransactionValidationException(
                    ("Exception while parsing JSON file." + ioException.getMessage()));
        }
    }
}
