package com.colorwalletalert.model;

import java.time.LocalDateTime;

public class CategorySpend {
    public Category category;
    public Float spendValue;
    public LocalDateTime spendDate;

    public CategorySpend() {}

    public CategorySpend(Category category, Float value) {
        this.category = category;
        this.spendValue = value;
        this.spendDate = LocalDateTime.now();
    }

    public Category getCategory() {
        return category;
    }

    public Float getSpendValue() {
        return spendValue;
    }

    public LocalDateTime getSpendDate() {
        return spendDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSpendValue(Float spendValue) {
        this.spendValue = spendValue;
    }

    public void setSpendDate(LocalDateTime spendDate) {
        this.spendDate = spendDate;
    }
}
