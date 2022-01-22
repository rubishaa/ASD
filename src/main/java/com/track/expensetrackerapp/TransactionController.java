package com.track.expensetrackerapp.controller;

import com.track.expensetrackerapp.dto.TransactionDto;
import com.track.expensetrackerapp.model.Transaction;
import com.track.expensetrackerapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @CrossOrigin
    @GetMapping
    ResponseEntity<Transaction> findTransactionById(@RequestParam Integer id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/filter")
    ResponseEntity<List<Transaction>> filterTransaction(@RequestParam String month, String year) {
        List<Transaction> transactionList = transactionService.findAllTransactionByFilterCriteria(month, year);
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(produces = "application/json")
    ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transaction) {
        Transaction createdTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    @CrossOrigin
    @DeleteMapping
    ResponseEntity<?> deleteTransactionById(@RequestParam Integer id) {
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
