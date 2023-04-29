package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.DifficultySelectionCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.Scanner;

public class DifficultySelectionDialogService {

  private final Scanner scanner;
  private final DifficultySelectionCliPort cliOutputPort;

  public DifficultySelectionDialogService() {
    scanner = new Scanner(System.in);
    cliOutputPort = DependencyFactory.getInstance().getDependency(DifficultySelectionCliPort.class);
  }

  public Difficulty selectDifficulty() {
    StringBuilder difficultyDialog = new StringBuilder("Select a difficulty: ");
    Difficulty.stream()
        .forEach(d -> difficultyDialog.append(d.getName()).append(" (")
            .append(d.getShortDifficultyName()).append(") "));
    DependencyFactory.getInstance().getDependency(CliOutputPort.class).write(difficultyDialog.toString());

    return successfulSelection();
  }

  private Difficulty successfulSelection() {

    Difficulty selectedDifficulty = null;
    while (selectedDifficulty == null) {
      String input = scanner.nextLine();

      selectedDifficulty = Difficulty.stream()
          .filter(d -> d.match(input))
          .findFirst()
          .orElse(null);
      if (selectedDifficulty == null) {
        cliOutputPort.writeNoEqualDifficulty();
      }
    }

    return selectedDifficulty;
  }
}
