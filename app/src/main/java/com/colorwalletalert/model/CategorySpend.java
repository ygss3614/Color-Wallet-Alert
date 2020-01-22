package com.colorwalletalert.model;

import java.time.LocalDateTime;

public class CategorySpend {
    public String categoryName;
    public Float spendValue;
    public int spendDay;
    public int spendMonth;
    public int spendYear;

    public CategorySpend() {}

    public CategorySpend(String categoryName, Float value) {
        this.categoryName = categoryName;
        this.spendValue = value;
        this.spendDay = LocalDateTime.now().toLocalDate().getDayOfMonth();
        this.spendMonth = LocalDateTime.now().toLocalDate().getMonthValue();
        this.spendYear = LocalDateTime.now().toLocalDate().getYear();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Float getSpendValue() {
        return spendValue;
    }



    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSpendValue(Float spendValue) {
        this.spendValue = spendValue;
    }

    public int getSpendDay() {
        return spendDay;
    }

    public int getSpendMonth() {
        return spendMonth;
    }

    public int getSpendYear() {
        return spendYear;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setSpendDay(int spendDay) {
        this.spendDay = spendDay;
    }

    public void setSpendMonth(int spendMonth) {
        this.spendMonth = spendMonth;
    }

    public void setSpendYear(int spendYear) {
        this.spendYear = spendYear;
    }
}
