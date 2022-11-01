package de.dhbw.karlsruhe.services;

import de.dhbw.karlsruhe.model.Difficulty;

import java.util.Scanner;

public class DifficultySelectionDialogService {

    Scanner scanner;

    public DifficultySelectionDialogService(){
        scanner = new Scanner(System.in);
    }

    public Difficulty selectDifficulty() {
        StringBuilder difficultyDialog = new StringBuilder("Select a difficulty: ");
        Difficulty.stream()
                .forEach(d -> difficultyDialog.append(d.toString()).append(" (")
                                .append(d.shortDifficultyName).append(") "));
        System.out.println(difficultyDialog);

        return successfulSelection();
    }

    private Difficulty successfulSelection(){

        Difficulty selectedDifficulty= null;
        while (selectedDifficulty == null) {
            String input = scanner.nextLine();

            selectedDifficulty= Difficulty.stream()
                    .filter(d -> d.match(input))
                    .findFirst()
                    .orElse(null);
        }

        return selectedDifficulty;
    }
}
