package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.services.UserService;

public class ChangeUserNameAction extends UserAction {

    private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
    private final String newUserName;

    public ChangeUserNameAction(String newUserName) {
        this.newUserName = newUserName;
    }

    @Override
    public void executeAction(User user) {
        userService.changeUserName(newUserName);
    }

}
