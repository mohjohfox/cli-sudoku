package de.dhbw.karlsruhe.domain.models.leaderboard;

import java.util.Comparator;

public class LeaderboardSaveEntryComparator implements Comparator<LeaderboardSaveEntry> {

  @Override
  public int compare(LeaderboardSaveEntry leaderboardSaveEntry1, LeaderboardSaveEntry leaderboardSaveEntry2) {
    Integer scoreLeaderboardSaveEntry1 = leaderboardSaveEntry1.getScore();
    Integer scoreLeaderboardSaveEntry2 = leaderboardSaveEntry2.getScore();

    return scoreLeaderboardSaveEntry1.compareTo(scoreLeaderboardSaveEntry2);
  }
}
