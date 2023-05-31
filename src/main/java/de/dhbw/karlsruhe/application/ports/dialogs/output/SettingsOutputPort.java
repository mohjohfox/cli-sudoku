package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.core.Setting;

public interface SettingsOutputPort {

  void settingsMenu(Setting setting);

  void invalidOption();

}
