package com.track.expensetrackerapp.Repository;

import com.track.expensetrackerapp.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}

