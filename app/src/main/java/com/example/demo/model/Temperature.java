package com.example.demo.model;

public class Temperature {
    private Double Value;
    private int Unit;

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }
}
