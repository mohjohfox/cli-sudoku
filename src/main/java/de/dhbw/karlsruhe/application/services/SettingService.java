package de.dhbw.karlsruhe.application.services;

import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.core.Setting;

public class SettingService {

  private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);

  public void toggleFieldValidation(Setting setting) {
    setting.setFieldValidation(!setting.getFieldValidation());
  }

  public void toggleValueHint(Setting setting) {
    setting.setValueHint(!setting.getValueHint());
  }

  public void toggleFixMistakes(Setting setting) {
    setting.setFixMistakes(!setting.getFixMistakes());
  }

  public Setting getSettingFromCurrentUser() {
    return userService.getUser(GameInformation.username).getSetting();
  }

  public boolean areHintsActivated() {
    return getSettingFromCurrentUser().getValueHint() || getSettingFromCurrentUser().getFieldValidation()
        || getSettingFromCurrentUser().getFixMistakes();
  }

}
