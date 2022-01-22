package com.track.expensetrackerapp.Repository;

import com.track.expensetrackerapp.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    @Query(value = "select e from Budget e where year(e.startDate) = ?1 and month(e.startDate) = ?2  and e.budgetType = ?3 Group by e.startDate,e.id ")
    List<Budget> getByYearAndMonthAndType(int year, int month, String budgetType);

    @Query(value = "select e from Budget e where year(e.startDate) = ?1 and month(e.startDate) = ?2 Group by e.startDate,e.id ")
    List<Budget> getByYearAndMonth(int year, int month);
}
