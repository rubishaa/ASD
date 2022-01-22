package com.track.expensetrackerapp.controller;

import com.track.expensetrackerapp.dto.BudgetDto;
import com.track.expensetrackerapp.dto.BudgetViewDto;
import com.track.expensetrackerapp.model.Budget;
import com.track.expensetrackerapp.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    ResponseEntity<Budget> findBudgetById(@RequestParam Integer id) {
        Budget budget = budgetService.findBudgetById(id);
        return new ResponseEntity<>(budget, HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<Budget> deleteBudgetById(@RequestParam Integer id) {
        budgetService.deleteBudgetById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    ResponseEntity<?> createBudget(@RequestBody BudgetDto budget) {
        Budget savedBudget = budgetService.saveBudget(budget);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }

    @GetMapping("/view")
    ResponseEntity<?> findBudgetViewByMonthAndYear(@RequestParam Integer month, @RequestParam Integer year) {
        BudgetViewDto budgets = budgetService.viewBudgetsByMonthYear(month, year);
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }
}
