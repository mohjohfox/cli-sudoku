package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.DifficultySelectionOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class DifficultySelectionCliAdapter implements DifficultySelectionOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void noEqualDifficulty() {
        cliOutputPort.writeLine("The input did not equal a difficulty. Please try again!");
    }

    @Override
    public void difficultOptions() {
        StringBuilder difficultyDialog = new StringBuilder("Select a difficulty: ");
        Difficulty.stream()
                .forEach(d -> difficultyDialog.append(d.getName()).append(" (")
                        .append(d.getShortDifficultyName()).append(") "));
        cliOutputPort.writeLine(difficultyDialog.toString());
    }
}
