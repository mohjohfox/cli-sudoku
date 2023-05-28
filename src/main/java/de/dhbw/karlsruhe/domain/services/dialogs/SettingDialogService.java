package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.input.InvalidInputException;
import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.models.user.actions.UserAction;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.SettingInputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.UserService;

public class SettingDialogService {

    private final SettingsOutputPort settingsOutputPort = DependencyFactory.getInstance().getDependency(SettingsOutputPort.class);
    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
    private final SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    private final SettingInputPort settingInputPort = DependencyFactory.getInstance().getDependency(SettingInputPort.class);

    public void settingDialog() {
        User user = userService.getUser(GameInformation.username);
        Setting setting = user.getSetting();
        settingsOutputPort.settingsMenu(setting);
        UserAction userAction = getUserAction(setting);
        userAction.executeAction("");
        userAction.executeAction(setting);
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