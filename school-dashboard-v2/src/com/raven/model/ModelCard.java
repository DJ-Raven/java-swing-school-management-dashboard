package com.raven.model;

import javax.swing.Icon;

public class ModelCard {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValues() {
        return values;
    }

    public void setValues(double values) {
        this.values = values;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelCard(String title, double values, int percentage, Icon icon) {
        this.title = title;
        this.values = values;
        this.percentage = percentage;
        this.icon = icon;
    }

    public ModelCard() {
    }

    private String title;
    private double values;
    private int percentage;
    private Icon icon;
}
