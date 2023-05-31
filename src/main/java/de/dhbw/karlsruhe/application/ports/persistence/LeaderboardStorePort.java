package de.dhbw.karlsruhe.application.ports.persistence;

import de.dhbw.karlsruhe.domain.models.leaderboard.Leaderboard;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardSaveEntry;
import java.util.List;

public interface LeaderboardStorePort {

  void saveLeaderboard(Leaderboard leaderboard);

  List<LeaderboardSaveEntry> loadSavedEntriesFromLeaderboard(int leaderboardTypeID);
}
