package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.output.ArcadeCliAdapter;
import de.dhbw.karlsruhe.domain.models.Difficulty;
import de.dhbw.karlsruhe.domain.models.Sudoku;
import de.dhbw.karlsruhe.domain.models.SudokuSize;
import de.dhbw.karlsruhe.domain.models.generation.SudokuGeneratorBacktracking;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.ArcadeOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.SudokuOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ArcadeDialogService {

    Random random = new Random();
    private Sudoku sudoku;
    Set<Integer> levelNumbers;
    private SudokuGeneratorBacktracking sgBacktracking = DependencyFactory.getInstance().getDependency(SudokuGeneratorBacktracking.class);
    private final ArcadeOutputPort arcadeOutputPort = DependencyFactory.getInstance().getDependency(ArcadeCliAdapter.class);
    private final SudokuOutputPort sudokuOutputPort = DependencyFactory.getInstance().getDependency(SudokuOutputPort.class);

    public ArcadeDialogService() {

    }

    public void startArcadeGame() {
        this.arcadeOutputPort.introduction();
        this.arcadeOutputPort.emptyLine();

        sudoku = sgBacktracking.generateSudoku(SudokuSize.SMALL, Difficulty.EASY);
        this.arcadeOutputPort.sudokuIntroduction();
        this.sudokuOutputPort.print(sudoku);
        this.arcadeOutputPort.emptyLine();

        this.startArcadeSolving();

        this.arcadeOutputPort.conclusion();
    }

    private void startArcadeSolving() {
        this.levelNumbers = new HashSet<>();
        int levelNumber;

        this.levelNumbers.add(1);

        while (levelNumbers.size() < 8) {
            levelNumber = random.nextInt(20) + 1;
            this.levelNumbers.add(levelNumber);
        }

        for (int level : levelNumbers) {
            this.loadAndPrintLevel(level);
        }

    }

    private void loadAndPrintLevel(int level) {
        switch (level) {
            case 1:
                this.arcadeOutputPort.levelOne();
                break;
            case 2:
                this.arcadeOutputPort.levelTwo();
                break;
            case 3:
                this.arcadeOutputPort.levelThree();
                break;
            case 4:
                this.arcadeOutputPort.levelFour();
                break;
            case 5:
                this.arcadeOutputPort.levelFive();
                break;
            case 6:
                this.arcadeOutputPort.levelSix();
                break;
            case 7:
                this.arcadeOutputPort.levelSeven();
                break;
            case 8:
                this.arcadeOutputPort.levelEight();
                break;
            case 9:
                this.arcadeOutputPort.levelNine();
                break;
            case 10:
                this.arcadeOutputPort.levelTen();
                break;
            case 11:
                this.arcadeOutputPort.levelEleven();
                break;
            case 12:
                this.arcadeOutputPort.levelTwelve();
                break;
            case 13:
                this.arcadeOutputPort.levelThirteen();
                break;
            case 14:
                this.arcadeOutputPort.levelFourteen();
                break;
            case 15:
                this.arcadeOutputPort.levelFifteen();
                break;
            case 16:
                this.arcadeOutputPort.levelSixteen();
                break;
            case 17:
                this.arcadeOutputPort.levelSeventeen();
                break;
            case 18:
                this.arcadeOutputPort.levelEighteen();
                break;
            case 19:
                this.arcadeOutputPort.levelNineteen();
                break;
            case 20:
                this.arcadeOutputPort.levelTwenty();
                break;
            default:
                throw new IllegalArgumentException("No possible level to load!");
        }

        this.arcadeOutputPort.emptyLine();

    }

}
