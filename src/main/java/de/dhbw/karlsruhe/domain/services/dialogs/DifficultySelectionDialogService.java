package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.DifficultySelectionOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class DifficultySelectionDialogService {

  private final DifficultySelectionOutputPort outputPort;
  private final InputPort inputPort;

  public DifficultySelectionDialogService() {
    inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    outputPort = DependencyFactory.getInstance().getDependency(DifficultySelectionOutputPort.class);
  }

  public Difficulty selectDifficulty() {
    outputPort.difficultOptions();
    return successfulSelection();
  }

  private Difficulty successfulSelection() {

    Difficulty selectedDifficulty = null;
    while (selectedDifficulty == null) {
      String input = inputPort.getInput();

      selectedDifficulty = Difficulty.stream()
          .filter(d -> d.match(input))
          .findFirst()
          .orElse(null);
      if (selectedDifficulty == null) {
        outputPort.noEqualDifficulty();
      }
    }

    return selectedDifficulty;
  }
}
