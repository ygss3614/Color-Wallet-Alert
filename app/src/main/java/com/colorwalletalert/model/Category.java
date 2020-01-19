package com.colorwalletalert.model;

import java.text.DecimalFormat;

public class Category {
    public String description;
    public Float target;
    public int iconPath;

    public Category() {}

    public Category(String description, Float target, int iconPath) {
        this.description = description;
        this.target = target;
        this.iconPath = iconPath;
    }

    public String getDescription() {
        return description;
    }

    public Float getTarget() {
        return target;
    }

    public int getIconPath() {
        return iconPath;
    }

    public Float roundTwoDecimals(Float d) {
        DecimalFormat twoDForm = new DecimalFormat("#,##");
        return Float.valueOf(twoDForm.format(d));
    }

    public Float getSuggestedDailySpend (){

        // TODO: calcular de acordo com os dias restantes do mês
        return roundTwoDecimals(this.target/30);
    }

}
