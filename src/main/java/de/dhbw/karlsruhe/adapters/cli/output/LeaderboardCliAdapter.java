package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class LeaderboardCliAdapter implements LeaderboardOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void startLeaderboard() {
        cliOutputPort.write("Leaderboard Dialog");
    }

    @Override
    public void displayLeaderboard(List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        cliOutputPort.write("Display Leaderboard");
    }
}
