package com.surepay.datalayer.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Data
@AllArgsConstructor
@Entity
@Table(name = "reports")
public class Reports {

    @Id
    @Column(name = "reference")
    private Long transactionReference;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "start_balance")
    private double startBalance;

    @Column(name = "mutation")
    private double mutation;

}
