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
        cliOutputPort.write("[3] " + checkFixMistakesActivation(setting) + " fix mistakes action");
        cliOutputPort.write("[4] Write '3 yourNewUserName' for changing your username");
        cliOutputPort.write("[5] Write '4 yourNewPassword' for changing your password");
        cliOutputPort.write("[6] Back to main menu");
    }

    @Override
    public void invalidOption() {
        cliOutputPort.write("Invalid option. Please choose an option by entering a number!");
    }

    private String checkValueHintActivation(Setting setting) {
        return setting.getValueHint() ? "Deactivate" : "Activate";
    }

    private String checkFieldValidationActivation(Setting setting) {
        return setting.getFieldValidation() ? "Deactivate" : "Activate";
    }

    private String checkFixMistakesActivation(Setting setting) {
        return setting.getFixMistakes() ? "Deactivate" : "Activate";
    }

}
