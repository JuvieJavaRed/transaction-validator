package com.surepay.datalayer.services;

import com.surepay.datalayer.entities.Reports;
import com.surepay.datalayer.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsRepositoryServiceImpl implements ReportsRepositoryService{

    private final ReportsRepository reportsRepository;

    @Autowired
    public ReportsRepositoryServiceImpl(ReportsRepository reportsRepository){
        this.reportsRepository = reportsRepository;
    }
    @Override
    public void saveReportTransactions(List<Reports> invalidTransactions) {
        reportsRepository.saveAll(invalidTransactions);
    }

    @Override
    public List<Reports> retrieveReportTransactions() {
        return reportsRepository.findAll();
    }
}
