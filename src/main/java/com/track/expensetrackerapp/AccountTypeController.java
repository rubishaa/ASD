package com.track.expensetrackerapp.controller;

import com.track.expensetrackerapp.dto.TransactionDto;
import com.track.expensetrackerapp.model.AccountType;
import com.track.expensetrackerapp.model.Transaction;
import com.track.expensetrackerapp.service.AccountTypeService;
import com.track.expensetrackerapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;

    public AccountTypeController(AccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @GetMapping
    ResponseEntity<List<AccountType>> findAllAccountType() {
        List<AccountType> accountTypes = accountTypeService.findAllAccountType();
        return new ResponseEntity<>(accountTypes, HttpStatus.OK);
    }
}
