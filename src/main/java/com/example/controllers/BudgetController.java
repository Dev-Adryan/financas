package com.example.controllers;

import com.example.models.Budget;
import com.example.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/budgets")
    public class BudgetController {

        @Autowired
        private BudgetRepository budgetRepository;

        @PostMapping(name = "/create")
        public Budget createBudget(@RequestBody Budget budget) {
            return budgetRepository.save(budget);
        }

        @GetMapping
        public List<Budget> getAllBudgets() {
            return budgetRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
            return budgetRepository.findById(id)
                    .map(budget -> ResponseEntity.ok().body(budget))
                    .orElse(ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budgetDetails) {
            return budgetRepository.findById(id)
                    .map(budget -> {
                        budget.setCategory(budgetDetails.getCategory());
                        budget.setLimitAmount(budgetDetails.getLimitAmount());
                        budget.setCurrentSpent(budgetDetails.getCurrentSpent());
                        budget.setMonth(budgetDetails.getMonth());
                        budget.setYear(budgetDetails.getYear());
                        Budget updatedBudget = budgetRepository.save(budget);
                        return ResponseEntity.ok().body(updatedBudget);
                    }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
            return budgetRepository.findById(id)
                    .map(budget -> {
                        budgetRepository.delete(budget);
                        return ResponseEntity.ok().build();
                    }).orElse(ResponseEntity.notFound().build());
        }
    }


