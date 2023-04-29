package de.dhbw.karlsruhe.domain.services.dialogs;

import de.dhbw.karlsruhe.domain.ports.dialogs.LeaderboardCliPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

public class LeaderboardDialogService {

  private final LeaderboardCliPort cliOutputPort;

  public LeaderboardDialogService() {
    cliOutputPort = DependencyFactory.getInstance().getDependency(LeaderboardCliPort.class);
  }

  public void startLeaderboardDialog() {
    cliOutputPort.writeStartLeaderboard();
  }
}
