package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.User;

public abstract class UserAction {

    public abstract void executeAction(User user);

}
