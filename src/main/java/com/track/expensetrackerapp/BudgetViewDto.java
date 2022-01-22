package com.track.expensetrackerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BudgetViewDto {
    private Double totalBudgetAmountForAll;
    private Double totalTransactionExpenseAmountForAll;
    private List<CategoryBudget> categoryBudgetList;

    @Data
    @AllArgsConstructor
    public static class CategoryBudget {
        private Double totalBudgetAmountForCategory;
        private Double totalExpenseAmountForCategory;
        private String categoryName;

    }
}
