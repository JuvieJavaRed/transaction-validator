package com.surepay.datalayer.repository;

import com.surepay.datalayer.entities.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {
}
