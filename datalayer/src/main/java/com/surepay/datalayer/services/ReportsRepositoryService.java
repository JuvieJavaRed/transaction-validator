package com.surepay.datalayer.services;

import com.surepay.datalayer.entities.Reports;

import java.util.List;

public interface ReportsRepositoryService {
    public void saveReportTransactions(List<Reports> invalidTransactions);
    public List<Reports> retrieveReportTransactions();
}
