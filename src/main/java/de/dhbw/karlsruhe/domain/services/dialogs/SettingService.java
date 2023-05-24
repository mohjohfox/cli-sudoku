package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.AppInformation;
import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.UserService;

public class SettingService {

    private final SettingsOutputPort settingsOutputPort = DependencyFactory.getInstance().getDependency(SettingsOutputPort.class);
    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);


    public void settingDialog() {
        User user = getUser(AppInformation.username);
        Setting setting = getUserSettings(AppInformation.username);
        settingsOutputPort.settingsMenu(setting);
        int userInput = inputPort.getInputAsInt();
        switch (userInput) {
            case 1 -> toggleValueHint(setting);
            case 2 -> toggleFieldValidation(setting);
        }
        user.setSetting(setting);
        userService.updateUser(user);
    }

    public Setting getUserSettings(String username) {
        return getUser(username).getSetting();
    }

    private void toggleFieldValidation(Setting setting) {
        setting.setFieldValidation(!setting.getFieldValidation());
    }

    private void toggleValueHint(Setting setting) {
        setting.setValueHint(!setting.getValueHint());
    }

    private User getUser(String username) {
        return userService.getUser(username);
    }

}
