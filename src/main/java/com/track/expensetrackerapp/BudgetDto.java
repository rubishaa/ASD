package com.track.expensetrackerapp.dto;

import lombok.Data;

@Data
public class BudgetDto {
    private Double amount;
    private String startDate;
    private String budgetType;
    private String categoryId;
}
