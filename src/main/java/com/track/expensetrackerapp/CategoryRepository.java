package com.track.expensetrackerapp.Repository;

import com.track.expensetrackerapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByCategoryType(String type);
}

