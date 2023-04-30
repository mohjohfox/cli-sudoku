package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.adapters.CliOutputPort;
import de.dhbw.karlsruhe.domain.ports.dialogs.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LeaderboardCliAdapter implements LeaderboardOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void writeStartLeaderboard() {
        cliOutputPort.write("Leaderboard Dialog");
    }
}
