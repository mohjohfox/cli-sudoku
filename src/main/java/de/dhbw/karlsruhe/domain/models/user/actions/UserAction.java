package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.domain.models.Setting;

public abstract class UserAction {

    public abstract void executeAction(String modifiedValue);
    
    public abstract void executeAction(Setting setting);

}
