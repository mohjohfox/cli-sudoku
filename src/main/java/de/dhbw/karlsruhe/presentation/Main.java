package de.dhbw.karlsruhe.presentation;

import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.DurationTrackService;
import de.dhbw.karlsruhe.application.services.EncryptionService;
import de.dhbw.karlsruhe.application.services.LogoutService;
import de.dhbw.karlsruhe.application.services.SettingService;
import de.dhbw.karlsruhe.application.services.SudokuValidatorService;
import de.dhbw.karlsruhe.application.services.UserService;
import de.dhbw.karlsruhe.application.services.dialogs.ArcadeDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.DifficultySelectionDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.LeaderboardDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.MathProblemDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.MenuDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.PlayDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.SettingDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.StartUpDialogService;
import de.dhbw.karlsruhe.application.services.dialogs.SudokuSelectionDialog;
import de.dhbw.karlsruhe.application.services.dialogs.TutorialDialogService;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.arcade.MathProblem;
import de.dhbw.karlsruhe.domain.models.arcade.MathProblemUsage;
import de.dhbw.karlsruhe.domain.models.generation.SudokuFieldsRemover;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorTransformation;
import de.dhbw.karlsruhe.domain.models.generation.SudokuTransformation;
import de.dhbw.karlsruhe.domain.models.leaderboard.Leaderboard;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardScoreCalculator;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.DurationTrackAdapter;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.LeaderboardStoreAdapter;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.UserAdapter;
import de.dhbw.karlsruhe.presentation.cli.input.InputCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.input.InputSplitter;
import de.dhbw.karlsruhe.presentation.cli.input.PlayInputCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.input.ScannerAdapter;
import de.dhbw.karlsruhe.presentation.cli.input.SettingInputCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.input.TutorialInputCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.ArcadeCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.CliOutputAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.DifficultySelectionCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.LeaderboardCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.LogoutCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.MenuCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.PlayCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.RulesCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.SettingsCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.StartUpCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.SudokuCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.SudokuSelectionCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.TutorialOutputCliAdapter;
import de.dhbw.karlsruhe.presentation.cli.output.UserCliAdapter;

public class Main {

  public static void main(String[] args) {
    injectDependencies();
    boolean desireToRun;
    LogoutService logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
    StartUpDialogService startUpDialogService = DependencyFactory.getInstance()
        .getDependency(StartUpDialogService.class);

    MenuDialogService menuDialogService = DependencyFactory.getInstance().getDependency(MenuDialogService.class);

    do {
      startUpDialogService.signIn();
      menuDialogService.startMenuDialog();

      desireToRun = logoutService.checkDesireToRun();
    } while (desireToRun);
  }

  private static void injectDependencies() {
    DependencyFactory dependencyFactory = DependencyFactory.getInstance();
    dependencyFactory.registerDependency(new MathProblem());
    dependencyFactory.registerDependency(new MathProblemUsage());
    dependencyFactory.registerDependency(new Leaderboard());
    dependencyFactory.registerDependency(new SudokuPersistenceAdapter(Location.PROD));
    dependencyFactory.registerDependency(new DurationTrackAdapter(Location.PROD));
    dependencyFactory.registerDependency(new DurationTrackService());
    dependencyFactory.registerDependency(new EncryptionService());
    dependencyFactory.registerDependency(new ScannerAdapter());
    dependencyFactory.registerDependency(new InputSplitter());
    dependencyFactory.registerDependency(new InputCliAdapter());
    dependencyFactory.registerDependency(new PlayInputCliAdapter());
    dependencyFactory.registerDependency(new CliOutputAdapter());
    dependencyFactory.registerDependency(new LeaderboardCliAdapter());
    dependencyFactory.registerDependency(new LeaderboardStoreAdapter(Location.PROD));
    dependencyFactory.registerDependency(new LeaderboardDialogService());
    dependencyFactory.registerDependency(new LeaderboardScoreCalculator());
    dependencyFactory.registerDependency(new RulesCliAdapter());
    dependencyFactory.registerDependency(new DifficultySelectionCliAdapter());
    dependencyFactory.registerDependency(new LogoutCliAdapter());
    dependencyFactory.registerDependency(new MenuCliAdapter());
    dependencyFactory.registerDependency(new PlayCliAdapter());
    dependencyFactory.registerDependency(new StartUpCliAdapter());
    dependencyFactory.registerDependency(new SudokuSelectionCliAdapter());
    dependencyFactory.registerDependency(new UserCliAdapter());
    dependencyFactory.registerDependency(new UserAdapter(Location.PROD));
    dependencyFactory.registerDependency(new SudokuCliAdapter());
    dependencyFactory.registerDependency(new SudokuSelectionDialog());
    dependencyFactory.registerDependency(new UserService());
    dependencyFactory.registerDependency(new SudokuGeneratorBacktracking());
    dependencyFactory.registerDependency(new SudokuValidatorService());
    dependencyFactory.registerDependency(new ArcadeCliAdapter());
    dependencyFactory.registerDependency(new SudokuGeneratorTransformation());
    dependencyFactory.registerDependency(new SudokuTransformation());
    dependencyFactory.registerDependency(new SettingsCliAdapter());
    dependencyFactory.registerDependency(new LogoutService());
    dependencyFactory.registerDependency(new SettingService());
    dependencyFactory.registerDependency(new SettingInputCliAdapter());
    dependencyFactory.registerDependency(new PlayDialogService());
    dependencyFactory.registerDependency(new SettingDialogService());
    dependencyFactory.registerDependency(new MenuDialogService());
    dependencyFactory.registerDependency(new StartUpDialogService());
    dependencyFactory.registerDependency(new DifficultySelectionDialogService());
    dependencyFactory.registerDependency(new SudokuFieldsRemover());
    dependencyFactory.registerDependency(new TutorialOutputCliAdapter());
    dependencyFactory.registerDependency(new TutorialInputCliAdapter());
    dependencyFactory.registerDependency(new TutorialDialogService());
    dependencyFactory.registerDependency(new MathProblemDialogService());
    dependencyFactory.registerDependency(new ArcadeDialogService());
  }

}
