package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.UserService;

public class ChangeUserNameAction extends UserAction {

    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);

    @Override
    public void executeAction(String modifiedValue) {
        userService.changeUserName(modifiedValue);
    }

    @Override
    public void executeAction(Setting setting) {

    }
}
