package com.example.dto;


import java.util.List;

public class MonthlyBalanceReport {

    private String month;
    private int year;
    private Double totalIncome;
    private Double totalExpenses;
    private Double balance;
    private List<CategoryExpense> details;

    public static class CategoryExpense {
        private String category;
        private Double amount;

        // Getters and Setters
        public CategoryExpense() {}


        public CategoryExpense(String category, Double amount) {
            this.category = category;
            this.amount = amount;




        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }
    }

    // Getters and Setters
    public String getMonth() {
        return month;

    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<CategoryExpense> getDetails() {
        return details;
    }

    public void setDetails(List<CategoryExpense> details) {
        this.details = details;
    }











}


