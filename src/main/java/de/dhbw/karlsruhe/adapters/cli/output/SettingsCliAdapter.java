package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SettingsOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class SettingsCliAdapter implements SettingsOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void settingsMenu(Setting setting) {
        cliOutputPort.writeLine("Settings Menu");
        cliOutputPort.writeLine("[1] " + checkValueHintActivation(setting) + " value hint");
        cliOutputPort.writeLine("[2] " + checkFieldValidationActivation(setting) + " field validation");
        cliOutputPort.writeLine("[3] " + checkFixMistakesActivation(setting) + " fix mistakes action");
        cliOutputPort.writeLine("[4] Write '3 yourNewUserName' for changing your username");
        cliOutputPort.writeLine("[5] Write '4 yourNewPassword' for changing your password");
        cliOutputPort.writeLine("[6] Back to main menu");
    }

    @Override
    public void invalidOption() {
        cliOutputPort.writeLine("Invalid option. Please choose an option by entering a number!");
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
