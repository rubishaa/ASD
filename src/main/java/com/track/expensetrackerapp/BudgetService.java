package com.track.expensetrackerapp.service;

import com.track.expensetrackerapp.Repository.BudgetRepository;
import com.track.expensetrackerapp.Repository.CategoryRepository;
import com.track.expensetrackerapp.Repository.TransactionRepository;
import com.track.expensetrackerapp.dto.BudgetDto;
import com.track.expensetrackerapp.dto.BudgetViewDto;
import com.track.expensetrackerapp.model.Budget;
import com.track.expensetrackerapp.model.Category;
import com.track.expensetrackerapp.model.Expense;
import com.track.expensetrackerapp.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.budgetRepository = budgetRepository;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public Budget findBudgetById(Integer id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isPresent()) {
            return budget.get();
        }
        return null;
    }

    public void deleteBudgetById(Integer id) {
        budgetRepository.deleteById(id);
    }

    public Budget saveBudget(BudgetDto budget) {

        Budget budget1 = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate startDate = LocalDate.parse(budget.getStartDate(), formatter);

        int month = startDate.getMonth().getValue();
        int year = startDate.getYear();


        if (!budget.getBudgetType().equals("ALL")) {
            //check category budget and update
            Optional<Category> byId = categoryRepository.findById(Integer.parseInt(budget.getCategoryId()));
            if (!byId.isPresent()) {
                return null;
            }
            Category category = byId.get();

            List<Budget> byYearMonthCategory = budgetRepository.getByYearAndMonthAndType(year, month, "CATEGORY");
            Optional<Budget> first = byYearMonthCategory.stream()
                    .filter(budget2 -> budget2.getCategory().getId() == Integer.parseInt(budget.getCategoryId()))
                    .findFirst();
            if (first.isPresent()) {
                Budget budget2 = first.get();
                budget2.setAmount(budget.getAmount());
                budget2.setStartDate(startDate);
                budget1 = budgetRepository.save(budget2);
            } else {
                Budget b = new Budget(budget.getAmount(),
                        budget.getBudgetType(), startDate, category);
                budget1 = budgetRepository.save(b);
            }


        } else {
            //check all budget and update
            List<Budget> all = budgetRepository.getByYearAndMonthAndType(year, month, "ALL");
            if (all != null && all.size() > 0) {
                all.get(0).setAmount(budget.getAmount());
                all.get(0).setStartDate(startDate);
                budget1 = budgetRepository.save(all.get(0));
            }
        }

        return budget1;
    }

    public BudgetViewDto viewBudgetsByMonthYear(int month, int year) {

        BudgetViewDto budgetViewDto = new BudgetViewDto();
        List<Budget> budgetList = budgetRepository.getByYearAndMonth(year, month);

        Map<Integer, Double> totalExpenseForEachBudgetCategory = new HashMap<>();


        for (Budget budget : budgetList) {
            if (budget.getBudgetType().equals("ALL")) {
                budgetViewDto.setTotalBudgetAmountForAll(budget.getAmount());
            } else {
                totalExpenseForEachBudgetCategory.put(budget.getCategory().getId(), budget.getAmount());
            }
        }

        List<Expense> transactionByYearAndMonth = transactionRepository.getTransactionByYearAndMonth(year, month);
//calculate total amount foreach category

        Double totalTransactionExpenseAmount = transactionByYearAndMonth.stream()
                .mapToDouble(Transaction::getAmount).sum();

        Map<Integer, Double> totalExpenseForEachCategory = new HashMap<>();

        List<BudgetViewDto.CategoryBudget> categoryBudgetList = new ArrayList<>();

        transactionByYearAndMonth.stream()
                .collect(Collectors.groupingBy(foo -> foo.getCategory().getId(),
                        Collectors.summingDouble(foo -> foo.getAmount())))
                .forEach((id, sumTargetCost) -> totalExpenseForEachCategory.put(id, sumTargetCost));

        transactionByYearAndMonth.stream()
                .collect(Collectors.groupingBy(Expense::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)))
                .forEach((category, sumTargetCost) ->
                        categoryBudgetList.add(new BudgetViewDto.CategoryBudget
                                (totalExpenseForEachBudgetCategory.get(category.getId()), sumTargetCost, category.getName())));

        budgetViewDto.setCategoryBudgetList(categoryBudgetList);
        budgetViewDto.setTotalTransactionExpenseAmountForAll(totalTransactionExpenseAmount);

        return budgetViewDto;
    }
}
