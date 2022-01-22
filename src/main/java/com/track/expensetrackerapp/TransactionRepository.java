package com.track.expensetrackerapp.Repository;

import com.track.expensetrackerapp.model.Budget;
import com.track.expensetrackerapp.model.Expense;
import com.track.expensetrackerapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "select e from Transaction e where year(e.startDate) = ?1 and month(e.startDate) = ?2  and e.transactionType ='EXPENSE' Group by e.startDate,e.id ")
    List<Expense> getTransactionByYearAndMonth(int year, int month);
}

