package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;
import de.dhbw.karlsruhe.domain.services.UserService;

public class SettingDialogService {

    private final SettingsOutputPort settingsOutputPort = DependencyFactory.getInstance().getDependency(SettingsOutputPort.class);
    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
    private final SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);

    public void settingDialog() {
        User user = userService.getUser(GameInformation.username);
        Setting setting = user.getSetting();
        settingsOutputPort.settingsMenu(setting);
        int userInput = -1;
        while (userInput == -1) {
            try {
                userInput = inputPort.getInputAsInt();

                if (userInput == 1 || userInput == 2 || userInput == 3) {
                    switch (userInput) {
                        case 1 -> settingService.toggleValueHint(setting);
                        case 2 -> settingService.toggleFieldValidation(setting);
                    }
                    user.setSetting(setting);
                    userService.updateUser(user);
                } else {
                    settingsOutputPort.settingsMenu(setting);
                    userInput = -1;
                }
            } catch (InvalidOptionException e) {
                settingsOutputPort.settingsMenu(setting);
            }
        }
    }
}