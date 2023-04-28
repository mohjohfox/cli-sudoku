package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.ports.CliOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LeaderboardDialogService {

  private final CliOutputPort cliOutputPort;

  public LeaderboardDialogService() {
    cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);
  }

  public void startLeaderboardDialog() {
    cliOutputPort.write("Leaderboard Dialog");
  }
}
