package de.dhbw.karlsruhe.domain.models.core;

import java.util.Objects;

public class Setting {

  private boolean valueHint;
  private boolean fieldValidation;
  private boolean fixMistakes;

  public Setting() {
    this.valueHint = true;
    this.fieldValidation = true;
    this.fixMistakes = true;
  }

  public Setting(boolean valueHint, boolean fieldValidation, boolean fixMistakes) {
    this.valueHint = valueHint;
    this.fieldValidation = fieldValidation;
    this.fixMistakes = fixMistakes;
  }

  public boolean getValueHint() {
    return valueHint;
  }

  public boolean getFieldValidation() {
    return fieldValidation;
  }

  public boolean getFixMistakes() {
    return fixMistakes;
  }

  @Override
  public String toString() {
    return "valueHint=" + valueHint + ",fieldValidation=" + fieldValidation + ",fixMistakes=" + fixMistakes;
  }

  public void setValueHint(boolean valueHint) {
    this.valueHint = valueHint;
  }

  public void setFieldValidation(boolean fieldValidation) {
    this.fieldValidation = fieldValidation;
  }

  public void setFixMistakes(boolean fixMistakes) {
    this.fixMistakes = fixMistakes;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Setting setting = (Setting) o;
    return valueHint == setting.valueHint && fieldValidation == setting.fieldValidation;
  }

  @Override
  public int hashCode() {
    return Objects.hash(valueHint, fieldValidation);
  }
}
