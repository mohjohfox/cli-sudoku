package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class LeaderboardDialogService {

    private final LeaderboardOutputPort outputPort;

    public LeaderboardDialogService() {
        outputPort = DependencyFactory.getInstance().getDependency(LeaderboardOutputPort.class);
    }

    public void startLeaderboardDialog() {
        outputPort.startLeaderboard();
    }

    public void displayLeaderboard(List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        outputPort.displayLeaderboard(leaderboardSaveEntries);
    }
}
