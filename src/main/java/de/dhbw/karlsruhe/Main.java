package de.dhbw.karlsruhe;

import de.dhbw.karlsruhe.adapters.cli.output.CliOutputAdapter;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.generation.SudokuTransformator;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.*;
import de.dhbw.karlsruhe.domain.services.dialogs.*;

public class Main {

  public static void main(String[] args) {
    injectDependencies();
    boolean desireToRun;
    LogoutService logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    StartUpDialogService startUpDialogService = DependencyFactory.getInstance().getDependency(StartUpDialogService.class);;
    MenuDialogService menuDialogService = DependencyFactory.getInstance().getDependency(MenuDialogService.class);;

    do {
      startUpDialogService.signIn();
      menuDialogService.startMenuDialog();

      desireToRun = logoutService.checkDesireToRun();
    } while (desireToRun);
  }

  private static void injectDependencies() {
    DependencyFactory dependencyFactory = DependencyFactory.getInstance();
    dependencyFactory.registerDependency(new EncryptionService());
    dependencyFactory.registerDependency(new SudokuSelectionDialog());
    dependencyFactory.registerDependency(new CliOutputAdapter());
    dependencyFactory.registerDependency(new UserService());
    dependencyFactory.registerDependency(new SudokuGeneratorBacktracking());
    dependencyFactory.registerDependency(new SudokuGeneratorTransformation());
    dependencyFactory.registerDependency(new SudokuTransformator());
    dependencyFactory.registerDependency(new LogoutService());
    dependencyFactory.registerDependency(new SudokuValidatorService());
    dependencyFactory.registerDependency(new PlayDialogService());
    dependencyFactory.registerDependency(new MenuDialogService());
    dependencyFactory.registerDependency(new StartUpDialogService());
    dependencyFactory.registerDependency(new Sudoku());
    dependencyFactory.registerDependency(new SudokuArray(new int[9][9]));
    dependencyFactory.registerDependency(new ScannerService());
    dependencyFactory.registerDependency(new SudokuFieldsRemover());
    dependencyFactory.registerDependency(new DifficultySelectionDialogService());
  }
}
