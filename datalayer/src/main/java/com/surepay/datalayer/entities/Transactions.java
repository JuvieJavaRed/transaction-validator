package com.surepay.datalayer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "transactions")
public class Transactions {
    @CsvBindByName(column = "Reference")
    @Id
    @Column(name = "reference")
    private long transactionReference;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    @CsvBindByName(column = "AccountNumber")
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "description")
    @CsvBindByName(column = "Description")
    private String description;

    @Column(name = "start_balance")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "Start Balance")
    private double startBalance;

    @Column(name = "mutation")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "Mutation")
    private double mutation;

    @Column(name = "end_balance")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CsvBindByName(column = "End Balance")
    private double endBalance;
}
