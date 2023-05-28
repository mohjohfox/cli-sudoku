package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.UserService;

public class ChangePasswordAction extends UserAction {

    private UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);

    @Override
    public void executeAction(String modifiedValue) {
        userService.changePassword(modifiedValue);
    }

    @Override
    public void executeAction(Setting setting) {

    }
}
