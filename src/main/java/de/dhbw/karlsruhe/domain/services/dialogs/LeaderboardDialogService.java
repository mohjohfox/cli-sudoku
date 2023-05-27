package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LeaderboardDialogService {

    private final LeaderboardOutputPort outputPort;

    public LeaderboardDialogService() {
        outputPort = DependencyFactory.getInstance().getDependency(LeaderboardOutputPort.class);
    }

    public void startLeaderboardDialog() {
        outputPort.startLeaderboard();
    }
}
