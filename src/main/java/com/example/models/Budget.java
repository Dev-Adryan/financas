package com.example.models;



import jakarta.persistence.*;

@Entity
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Double limitAmount;
    private Double currentSpent;
    private String month;
    private int year;


    // Getters and Setters
    public Budget() {
    }

    public Budget(String category, Double limitAmount, Double currentSpent, String month, int year) {
        this.category = category;
        this.limitAmount = limitAmount;
        this.currentSpent = currentSpent;
        this.month = month;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(Double limitAmount) {
        this.limitAmount = limitAmount;
    }

    public Double getCurrentSpent() {
        return currentSpent;
    }

    public void setCurrentSpent(Double currentSpent) {
        this.currentSpent = currentSpent;
    }

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

}

