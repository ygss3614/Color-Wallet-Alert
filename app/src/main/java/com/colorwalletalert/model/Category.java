package com.colorwalletalert.model;

public class Category {
    public String description;
    public Float target;
    public String iconPath;

    public Category() {}

    public Category(String description, Float target, String iconPath) {
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

    public String getIconPath() {
        return iconPath;
    }
}
