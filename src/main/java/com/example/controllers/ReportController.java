package com.example.controllers;

import com.example.dto.MonthlyBalanceReport;
import com.example.models.Transaction;
import com.example.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/monthly-balance")
    public MonthlyBalanceReport getMonthlyBalanceReport(@RequestParam String month, @RequestParam int year) {
        YearMonth yearMonth = YearMonth.of(year, LocalDate.parse(month + "-01").getMonthValue());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Transaction> transactions = transactionRepository.findAll()
                .stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        Double totalIncome = transactions.stream()
                .filter(t -> t.getType().equals("income"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        Double totalExpenses = transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();

        List<MonthlyBalanceReport.CategoryExpense> details = transactions.stream()
                .filter(t -> t.getType().equals("expense"))
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)))
                .entrySet()
                .stream()
                .map(entry -> {
                    MonthlyBalanceReport.CategoryExpense ce = new MonthlyBalanceReport.CategoryExpense("", 121.0);
                    ce.setCategory(entry.getKey());
                    ce.setAmount(entry.getValue());
                    return ce;
                })
                .collect(Collectors.toList());

        MonthlyBalanceReport report = new MonthlyBalanceReport();
        report.setMonth(month);
        report.setYear(year);
        report.setTotalIncome(totalIncome);
        report.setTotalExpenses(totalExpenses);
        report.setBalance(totalIncome - totalExpenses);
        report.setDetails(details);

        return report;
    }
}

