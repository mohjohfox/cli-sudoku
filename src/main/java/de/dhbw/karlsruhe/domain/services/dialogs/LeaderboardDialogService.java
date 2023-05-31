package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.adapters.cli.output.CliOutputPort;
import de.dhbw.karlsruhe.domain.models.*;
import de.dhbw.karlsruhe.domain.ports.dialogs.input.InputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.ports.persistence.LeaderboardStorePort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardDialogService {

    private final LeaderboardOutputPort outputPort;
    private final InputPort inputPort;
    private final LeaderboardStorePort leaderboardStorePort;

    public LeaderboardDialogService() {
        this.outputPort = DependencyFactory.getInstance().getDependency(LeaderboardOutputPort.class);
        this.inputPort = DependencyFactory.getInstance().getDependency(InputPort.class);
        this.leaderboardStorePort = DependencyFactory.getInstance().getDependency(LeaderboardStorePort.class);
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
                leaderboardSaveEntries = this.leaderboardStorePort.loadSavedEntriesFromLeaderboard(1);
                this.displayLeaderboard(new Leaderboard(LeaderboardType.COMPLETE, leaderboardSaveEntries));

                break;
            case 2:
                leaderboardSaveEntries = this.leaderboardStorePort.loadSavedEntriesFromLeaderboard(2);
                this.displayLeaderboard(new Leaderboard(LeaderboardType.SOLVINGTIME, leaderboardSaveEntries));

                break;
            case 3:
                leaderboardSaveEntries = this.leaderboardStorePort.loadSavedEntriesFromLeaderboard(3);
                this.displayLeaderboard(new Leaderboard(LeaderboardType.DIFFICULTY_EASY, leaderboardSaveEntries));

                break;
            case 4:
                leaderboardSaveEntries = this.leaderboardStorePort.loadSavedEntriesFromLeaderboard(4);
                this.displayLeaderboard(new Leaderboard(LeaderboardType.DIFFICULTY_MEDIUM, leaderboardSaveEntries));

                break;
            case 5:
                leaderboardSaveEntries = this.leaderboardStorePort.loadSavedEntriesFromLeaderboard(5);
                this.displayLeaderboard(new Leaderboard(LeaderboardType.DIFFICULTY_HARD, leaderboardSaveEntries));

                break;
            default:
                outputPort.noLeaderboardDisplayed();

                break;
        }
    }

    private void displayLeaderboard(Leaderboard leaderboard) {
        LeaderboardType leaderboardType = leaderboard.getLeaderboardType();
        List<LeaderboardSaveEntry> leaderboardSaveEntries = leaderboard.getLeaderboardSaveEntries();

        outputPort.leaderboardExplanation(leaderboardType);
        outputPort.writeEmptyLine();

        if (leaderboardSaveEntries.size() == 0) {
            outputPort.noLeaderboardEntriesYet();
            outputPort.writeEmptyLine();
            return;
        }

        this.sortLeaderboardEntries(leaderboardSaveEntries);

        outputPort.displayLeaderboard(leaderboard);
    }

    private void sortLeaderboardEntries(List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        leaderboardSaveEntries.sort(new LeaderboardSaveEntryComparator());
    }

}
