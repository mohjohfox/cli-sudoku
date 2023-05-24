package de.dhbw.karlsruhe.domain.models;

public class Setting {

    private boolean valueHint;
    private boolean fieldValidation;

    public Setting() {
        this.valueHint = false;
        this.fieldValidation = false;
    }

    public Setting(boolean valueHint, boolean fieldValidation) {
        this.valueHint = valueHint;
        this.fieldValidation = fieldValidation;
    }

    public boolean getValueHint() {
        return valueHint;
    }

    public boolean getFieldValidation() {
        return fieldValidation;
    }

    @Override
    public String toString() {
        return "valueHint=" + valueHint + ",fieldValidation=" + fieldValidation;
    }

    public void setValueHint(boolean valueHint) {
        this.valueHint = valueHint;
    }

    public void setFieldValidation(boolean fieldValidation) {
        this.fieldValidation = fieldValidation;
    }
}
