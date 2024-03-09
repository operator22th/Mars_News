package com.example.yinshaofeng.model;

public class Category {
    private String name;
    private int iconResId;

    public Category(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}