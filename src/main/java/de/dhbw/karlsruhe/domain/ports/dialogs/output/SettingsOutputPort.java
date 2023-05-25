package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.Setting;

public interface SettingsOutputPort {

    void settingsMenu(Setting setting);

    void invalidOption();

}
