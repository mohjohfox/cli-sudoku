package de.dhbw.karlsruhe.application.services.dialogs;

import de.dhbw.karlsruhe.application.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.input.SettingInputPort;
import de.dhbw.karlsruhe.application.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.SettingService;
import de.dhbw.karlsruhe.application.services.UserService;
import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.core.Setting;
import de.dhbw.karlsruhe.domain.models.core.User;
import de.dhbw.karlsruhe.domain.models.user.actions.UserAction;
import de.dhbw.karlsruhe.presentation.cli.input.InvalidInputException;

public class SettingDialogService {

  private final SettingsOutputPort settingsOutputPort = DependencyFactory.getInstance()
      .getDependency(SettingsOutputPort.class);
  private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
  private final SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);
  private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
  private final SettingInputPort settingInputPort = DependencyFactory.getInstance()
      .getDependency(SettingInputPort.class);

  public void settingDialog() {
    User user = userService.getUser(GameInformation.username);
    Setting setting = user.getSetting();
    settingsOutputPort.settingsMenu(setting);
    UserAction userAction = getUserAction(setting);
    userAction.executeAction(user);
    updateUserSettings(user, setting);
  }

  private UserAction getUserAction(Setting setting) {
    UserAction userAction = null;

    while (userAction == null) {
      try {
        userAction = settingInputPort.getUserAction();
      } catch (InvalidInputException ex) {
        printInvalidOptionWarning(setting);
      }
    }
    return userAction;
  }

  private void printInvalidOptionWarning(Setting setting) {
    settingsOutputPort.invalidOption();
    settingsOutputPort.settingsMenu(setting);
  }

  private void updateUserSettings(User user, Setting setting) {
    user.setSetting(setting);
    userService.updateUser(user);
  }

}
