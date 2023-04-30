package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.ports.dialogs.DifficultySelectionOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.Scanner;

public class DifficultySelectionDialogService {

  private final Scanner scanner;
  private final DifficultySelectionOutputPort outputPort;

  public DifficultySelectionDialogService() {
    scanner = new Scanner(System.in);
    outputPort = DependencyFactory.getInstance().getDependency(DifficultySelectionOutputPort.class);
  }

  public Difficulty selectDifficulty() {
    outputPort.difficultOptions();
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
        outputPort.noEqualDifficulty();
      }
    }

    return selectedDifficulty;
  }
}
