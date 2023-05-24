package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.AppInformation;
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
        User user = userService.getUser(AppInformation.username);
        Setting setting = user.getSetting();
        settingsOutputPort.settingsMenu(setting);
        int userInput = inputPort.getInputAsInt();
        switch (userInput) {
            case 1 -> settingService.toggleValueHint(setting);
            case 2 -> settingService.toggleFieldValidation(setting);
        }
        user.setSetting(setting);
        userService.updateUser(user);
    }
}