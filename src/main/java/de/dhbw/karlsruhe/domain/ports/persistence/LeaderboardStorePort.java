package de.dhbw.karlsruhe.domain.ports.persistence;

import de.dhbw.karlsruhe.domain.models.Leaderboard;
import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;

import java.util.List;

public interface LeaderboardStorePort {

    void saveLeaderboard(Leaderboard leaderboard);

    List<LeaderboardSaveEntry> loadSavedEntriesFromLeaderboard(int leaderboardTypeID);
}
