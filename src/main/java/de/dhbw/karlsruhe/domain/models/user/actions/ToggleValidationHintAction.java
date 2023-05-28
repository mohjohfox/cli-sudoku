package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.SettingService;

public class ToggleValidationHintAction extends UserAction {

    private final SettingService settingService = DependencyFactory.getInstance().getDependency(SettingService.class);

    @Override
    public void executeAction(User user) {
        settingService.toggleFieldValidation(user.getSetting());
    }
}
