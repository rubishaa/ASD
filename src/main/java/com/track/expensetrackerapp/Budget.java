package com.track.expensetrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Double amount;
    private String budgetType;
    private LocalDate startDate;
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Budget(Double amount, String budgetType, LocalDate startDate, Category category) {
        this.amount = amount;
        this.budgetType = budgetType;
        this.startDate = startDate;
        this.category = category;
    }
}