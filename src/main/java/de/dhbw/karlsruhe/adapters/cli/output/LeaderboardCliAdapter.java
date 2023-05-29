package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.models.LeaderboardType;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class LeaderboardCliAdapter implements LeaderboardOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void displayLeaderboardOptions() {
        cliOutputPort.write("Please select a leaderboard:");

        cliOutputPort.write("[" + LeaderboardType.COMPLETE.getTypeID() + "]" + LeaderboardType.COMPLETE.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.SOLVINGTIME.getTypeID() + "]" + LeaderboardType.SOLVINGTIME.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_EASY.getTypeID() + "]" + LeaderboardType.DIFFICULTY_EASY.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_MEDIUM.getTypeID() + "]" + LeaderboardType.DIFFICULTY_MEDIUM.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_HARD.getTypeID() + "]" + LeaderboardType.DIFFICULTY_HARD.getRepresentation());
    }

    @Override
    public void displayLeaderboard(String leaderboardTypeRepresentation, List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        StringBuilder leaderboardDialog = new StringBuilder(leaderboardTypeRepresentation + "Leaderboard:");

        leaderboardDialog.append(System.getProperty("line.separator"));

        for (LeaderboardSaveEntry leaderboardSaveEntry : leaderboardSaveEntries) {
            leaderboardDialog.append(leaderboardSaveEntry.getFormattedLeaderboardSaveEntry());
            leaderboardDialog.append(System.getProperty("line.separator"));
        }

        cliOutputPort.write(leaderboardDialog.toString());

    }

    @Override
    public void invalidInput() {
        cliOutputPort.write("This isn't a valid option!");
    }

    @Override
    public void noLeaderboardDisplayed() {
        cliOutputPort.write("No Leaderboard displayed!");
    }

    @Override
    public void noLeaderboardEntriesYet() {
        cliOutputPort.write("No Leaderboard entries yet!");
    }
}
