package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SettingsCliAdapter implements SettingsOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void settingsMenu(Setting setting) {
        cliOutputPort.write("Settings Menu");
        cliOutputPort.write("[1] " + checkValueHintActivation(setting) + " value hint");
        cliOutputPort.write("[2] " + checkFieldValidationActivation(setting) + " field validation");
        cliOutputPort.write("[3] Back to main menu");
    }

    private String checkValueHintActivation(Setting setting) {
        return setting.getValueHint() ? "Deactivate" : "Activate";
    }

    private String checkFieldValidationActivation(Setting setting) {
        return setting.getFieldValidation() ? "Deactivate" : "Activate";
    }

}
