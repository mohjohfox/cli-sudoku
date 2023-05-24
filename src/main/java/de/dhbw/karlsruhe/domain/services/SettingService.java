package de.dhbw.karlsruhe.domain.services;

import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.Setting;

public class SettingService {

    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);

    public void toggleFieldValidation(Setting setting) {
        setting.setFieldValidation(!setting.getFieldValidation());
    }

    public void toggleValueHint(Setting setting) {
        setting.setValueHint(!setting.getValueHint());
    }

    public Setting getSetting() {
        return userService.getUser(GameInformation.username).getSetting();
    }

}
