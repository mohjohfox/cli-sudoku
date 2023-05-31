package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.SettingService;
import de.dhbw.karlsruhe.domain.models.core.User;

public class ToggleValidationHintAction extends UserAction {

  private final SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);

  @Override
  public void executeAction(User user) {
    settingService.toggleFieldValidation(user.getSetting());
  }
}
