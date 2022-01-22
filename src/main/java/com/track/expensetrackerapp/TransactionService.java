package com.track.expensetrackerapp.service;

import com.track.expensetrackerapp.Repository.AccountTypeRepository;
import com.track.expensetrackerapp.Repository.CategoryRepository;
import com.track.expensetrackerapp.Repository.TransactionRepository;
import com.track.expensetrackerapp.dto.TransactionDto;
import com.track.expensetrackerapp.model.AccountType;
import com.track.expensetrackerapp.model.Category;
import com.track.expensetrackerapp.model.Expense;
import com.track.expensetrackerapp.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final AccountTypeRepository accountTypeRepository;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, AccountTypeRepository accountTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    public List<Transaction> findAllTransactionByFilterCriteria(String month, String year) {
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> transactionList = transactionRepository.findAll();
        if (!transactionList.isEmpty()) {
            for (Transaction tr : transactionList) {
                if (tr.getStartDate().getMonth().getValue() == Integer.parseInt(month)
                        && tr.getStartDate().getYear() == Integer.parseInt(year)) {
                    transactions.add(tr);
                }

            }
        }
        return transactions;

    }

    public Transaction findTransactionById(Integer id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElse(null);
    }

    public Transaction saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = null;
        Optional<Category> byId = categoryRepository.findById(Integer.parseInt(transactionDto.getCategoryId()));
        String startDate = transactionDto.getStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/d");
        LocalDate parse = LocalDate.parse(startDate, formatter);

        if (byId.isPresent()) {
            Transaction tr = new Expense(transactionDto.getAmount(),
                    transactionDto.getDescription(), parse,
                    transactionDto.getTransactionType(), byId.get());
            Optional<AccountType> accountTypeById = accountTypeRepository.findById(Integer.parseInt(transactionDto.getAccountTypeId()));

            if (accountTypeById.isPresent()) {
                tr.setAccountType(accountTypeById.get());
            } else {
                return null;
            }
            transaction = transactionRepository.save(tr);
        }
        return transaction;
    }

    public void deleteTransactionById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
