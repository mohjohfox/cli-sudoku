package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.ports.LeaderboardCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LeaderboardAdapter implements LeaderboardCliPort {

    private final CliOutputAdapter cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputAdapter.class);

    @Override
    public void writeStartLeaderboard() {
        cliOutputPort.write("Leaderboard Dialog");
    }
}
