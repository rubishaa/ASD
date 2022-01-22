package com.track.expensetrackerapp.service;

import com.track.expensetrackerapp.Repository.AccountTypeRepository;
import com.track.expensetrackerapp.Repository.CategoryRepository;
import com.track.expensetrackerapp.model.AccountType;
import com.track.expensetrackerapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeService {

    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public List<AccountType> findAllAccountType() {
        return accountTypeRepository.findAll();
    }

}
