package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.InvalidOptionException;
import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.models.LeaderboardType;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardDialogService {

    private final LeaderboardOutputPort outputPort;
    private final InputPort inputPort;

    public LeaderboardDialogService() {
        outputPort = DependencyFactory.getInstance().getDependency(LeaderboardOutputPort.class);
        inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
    }

    public void startLeaderboardDialog() {
        outputPort.displayLeaderboardOptions();

        int userInput = this.awaitUserInput();

        this.loadAndDisplayCorrectLeaderboard(userInput);
    }

    private int awaitUserInput() {
        int userInput = -1;

        while (userInput == -1) {
            try {
                userInput = inputPort.getInputAsInt();
                if (!this.validateInputIsInRange(userInput)) {
                    userInput = -1;
                    outputPort.invalidInput();
                    outputPort.displayLeaderboardOptions();
                }
            } catch (InvalidOptionException e) {
                throw new RuntimeException(e);
            }
        }

        return userInput;
    }

    private boolean validateInputIsInRange(int userInput) {
        if (userInput >= 1 && userInput <= 5) {
            return true;
        }

        return false;
    }

    private void loadAndDisplayCorrectLeaderboard(int userInput) {
        List<LeaderboardSaveEntry> leaderboardSaveEntries;

        switch (userInput) {
            case 1:
                leaderboardSaveEntries = this.loadLeaderboardEntries(1);
                this.displayLeaderboard(LeaderboardType.COMPLETE.getRepresentation(), leaderboardSaveEntries);

                break;
            case 2:
                leaderboardSaveEntries = this.loadLeaderboardEntries(2);
                this.displayLeaderboard(LeaderboardType.SOLVINGTIME.getRepresentation(), leaderboardSaveEntries);

                break;
            case 3:
                leaderboardSaveEntries = this.loadLeaderboardEntries(3);
                this.displayLeaderboard(LeaderboardType.DIFFICULTY_EASY.getRepresentation(), leaderboardSaveEntries);

                break;
            case 4:
                leaderboardSaveEntries = this.loadLeaderboardEntries(4);
                this.displayLeaderboard(LeaderboardType.DIFFICULTY_MEDIUM.getRepresentation(), leaderboardSaveEntries);

                break;
            case 5:
                leaderboardSaveEntries = this.loadLeaderboardEntries(5);
                this.displayLeaderboard(LeaderboardType.DIFFICULTY_HARD.getRepresentation(), leaderboardSaveEntries);

                break;
            default:
                outputPort.noLeaderboardDisplayed();

                break;
        }
    }

    private List<LeaderboardSaveEntry> loadLeaderboardEntries(int leaderboardTypeID) {
        return null;
    }

    private void displayLeaderboard(String leaderboardTypeRepresentation, List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        outputPort.displayLeaderboard(leaderboardTypeRepresentation, leaderboardSaveEntries);
    }
}
