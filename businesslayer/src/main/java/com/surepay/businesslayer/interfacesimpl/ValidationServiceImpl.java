package com.surepay.businesslayer.interfacesimpl;

import com.surepay.businesslayer.interfaces.ValidationService;
import com.surepay.datalayer.entities.Transactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ValidationServiceImpl implements ValidationService {
    /**
     * Validate transaction records list.
     *
     * @param transactionsList - provides list of records that needs to be validated the transaction records
     * @return the list Transactions - returns list of complex object type Transactions
     */
    @Override
    public List<Transactions> validateTransactionRecords(List<Transactions> transactionsList) {
        Set<Long> uniqueRecords = new HashSet<>();

        Set<Long> duplicateRecordsSet = transactionsList.stream()
                .filter(transactionRecordDTO -> !uniqueRecords.add(transactionRecordDTO.getTransactionReference()))
                .map(Transactions::getTransactionReference).collect(Collectors.toCollection(LinkedHashSet::new));

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return transactionsList.stream()
                .filter(transactions -> (duplicateRecordsSet.contains(transactions.getTransactionReference())
                        || transactions.getEndBalance() != Double.parseDouble
                        (decimalFormat.format(transactions.getMutation() + transactions.getStartBalance()))))
                .collect(Collectors.toList());
    }
}
