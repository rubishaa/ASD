package com.track.expensetrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Transaction {
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;


    public Expense(Double amount, String description, LocalDate startDate, String transactionType, Category category) {
        super(amount, description, startDate, transactionType);
        this.category = category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
