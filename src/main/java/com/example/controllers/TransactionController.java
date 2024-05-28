package com.example.controllers;

import com.example.models.Transaction;
import com.example.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> ResponseEntity.ok().body(transaction))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transactionDetails) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transaction.setType(transactionDetails.getType());
                    transaction.setAmount(transactionDetails.getAmount());
                    transaction.setCategory(transactionDetails.getCategory());
                    transaction.setDate(transactionDetails.getDate());
                    transaction.setDescription(transactionDetails.getDescription());
                    Transaction updatedTransaction = transactionRepository.save(transaction);
                    return ResponseEntity.ok().body(updatedTransaction);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        return transactionRepository.findById(id)
                .map(transaction -> {
                    transactionRepository.delete(transaction);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}

