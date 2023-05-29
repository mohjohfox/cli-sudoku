package de.dhbw.karlsruhe.domain.models;

import java.util.Objects;

public class Setting {

    private boolean valueHint;
    private boolean fieldValidation;

    public Setting() {
        this.valueHint = true;
        this.fieldValidation = true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting setting = (Setting) o;
        return valueHint == setting.valueHint && fieldValidation == setting.fieldValidation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueHint, fieldValidation);
    }
}
