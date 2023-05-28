package de.dhbw.karlsruhe.adapters;

import de.dhbw.karlsruhe.adapters.cli.input.InputCliAdapter;
import de.dhbw.karlsruhe.adapters.cli.input.PlayInputCliAdapter;
import de.dhbw.karlsruhe.adapters.cli.input.ScannerAdapter;
import de.dhbw.karlsruhe.adapters.cli.input.SettingInputCliAdapter;
import de.dhbw.karlsruhe.adapters.cli.output.*;
import de.dhbw.karlsruhe.adapters.persistence.DurationTrackAdapter;
import de.dhbw.karlsruhe.adapters.persistence.SudokuPersistenceAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.generation.*;
import de.dhbw.karlsruhe.domain.models.wrapper.SudokuArray;
import de.dhbw.karlsruhe.domain.services.*;
import de.dhbw.karlsruhe.domain.services.dialogs.*;

public class Main {

    public static void main(String[] args) {
        injectDependencies();
        boolean desireToRun;
        LogoutService logoutService = DependencyFactory.getInstance().getDependency(LogoutService.class);
        StartUpDialogService startUpDialogService = DependencyFactory.getInstance().getDependency(StartUpDialogService.class);

        MenuDialogService menuDialogService = DependencyFactory.getInstance().getDependency(MenuDialogService.class);

        do {
            startUpDialogService.signIn();
            menuDialogService.startMenuDialog();

            desireToRun = logoutService.checkDesireToRun();
        } while (desireToRun);
    }

    private static void injectDependencies() {
        DependencyFactory dependencyFactory = DependencyFactory.getInstance();
        dependencyFactory.registerDependency(new SudokuPersistenceAdapter(Location.PROD));
        dependencyFactory.registerDependency(new DurationTrackAdapter(Location.PROD));
        dependencyFactory.registerDependency(new DurationTrackService());
        dependencyFactory.registerDependency(new EncryptionService());
        dependencyFactory.registerDependency(new ScannerAdapter());
        dependencyFactory.registerDependency(new InputCliAdapter());
        dependencyFactory.registerDependency(new PlayInputCliAdapter());
        dependencyFactory.registerDependency(new CliOutputAdapter());
        dependencyFactory.registerDependency(new DifficultySelectionCliAdapter());
        dependencyFactory.registerDependency(new LeaderboardCliAdapter());
        dependencyFactory.registerDependency(new LogoutCliAdapter());
        dependencyFactory.registerDependency(new MenuCliAdapter());
        dependencyFactory.registerDependency(new PlayCliAdapter());
        dependencyFactory.registerDependency(new StartUpCliAdapter());
        dependencyFactory.registerDependency(new SudokuSelectionCliAdapter());
        dependencyFactory.registerDependency(new UserCliAdapter());
        dependencyFactory.registerDependency(new SudokuCliAdapter());
        dependencyFactory.registerDependency(new SudokuSelectionDialog());
        dependencyFactory.registerDependency(new UserService());
        dependencyFactory.registerDependency(new SudokuGeneratorBacktracking());
        dependencyFactory.registerDependency(new SudokuGeneratorTransformation());
        dependencyFactory.registerDependency(new SudokuTransformation());
        dependencyFactory.registerDependency(new SettingsCliAdapter());
        dependencyFactory.registerDependency(new LogoutService());
        dependencyFactory.registerDependency(new SudokuValidatorService());
        dependencyFactory.registerDependency(new SettingService());
        dependencyFactory.registerDependency(new SettingInputCliAdapter());
        dependencyFactory.registerDependency(new PlayDialogService());
        dependencyFactory.registerDependency(new SettingDialogService());
        dependencyFactory.registerDependency(new MenuDialogService());
        dependencyFactory.registerDependency(new StartUpDialogService());
        dependencyFactory.registerDependency(new DifficultySelectionDialogService());
        dependencyFactory.registerDependency(new SudokuFieldsRemover());
    }

}
