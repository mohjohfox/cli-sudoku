package de.dhbw.karlsruhe.application.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.leaderboard.Leaderboard;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardType;

public interface LeaderboardOutputPort {

  void displayLeaderboardOptions();

  void displayLeaderboard(Leaderboard leaderboard);

  void invalidInput();

  void noLeaderboardDisplayed();

  void noLeaderboardEntriesYet();

  void writeEmptyLine();

  void leaderboardExplanation(LeaderboardType leaderboardType);

  void displayLeaderboardIntroduction();
}
