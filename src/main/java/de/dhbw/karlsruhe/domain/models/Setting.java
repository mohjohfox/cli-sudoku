package de.dhbw.karlsruhe.domain.models;

public class Setting {

    private boolean valueHint;
    private boolean fieldValidation;

    public Setting() {
        this.valueHint = false;
        this.fieldValidation = false;
    }

    public boolean getValueHint() {
        return valueHint;
    }

    public boolean getFieldValidation() {
        return fieldValidation;
    }
}
