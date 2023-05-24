package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.UserService;

public class SettingService {

    private final SettingsOutputPort settingsOutputPort = DependencyFactory.getInstance().getDependency(SettingsOutputPort.class);
    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
    private final InputPort inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);


    public void settingDialog() {
        settingsOutputPort.settingsMenu(getUserSettings());
        int userInput = inputPort.getInputAsInt();
        switch (userInput) {
            case 1 -> toggleValueHint();
            case 2 -> toggleFieldValidation();
        }
    }

    private void toggleFieldValidation() {

    }

    private void toggleValueHint() {

    }

    private Setting getUserSettings() {
        // TODO: 2021-05-31 implement
        // return userService.getUser().getSettings();
        return new Setting();
    }

}
