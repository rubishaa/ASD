package com.track.expensetrackerapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Double amount;

    @Column
    private String description;

    @Column(name = "start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate startDate;

   @Column(name = "transaction_type")
    private String transactionType;

    @OneToOne
    @JoinColumn(name = "account_type_id", referencedColumnName = "id")
    private AccountType accountType;

    public Transaction(Double amount, String description, LocalDate startDate, String transactionType) {
        this.amount = amount;
        this.description = description;
        this.startDate = startDate;
        this.transactionType = transactionType;
    }
}
