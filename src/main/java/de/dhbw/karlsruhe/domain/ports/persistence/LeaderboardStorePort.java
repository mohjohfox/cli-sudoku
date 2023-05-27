package de.dhbw.karlsruhe.domain.ports.persistence;

import de.dhbw.karlsruhe.domain.models.Leaderboard;

public interface LeaderboardStorePort {

    void saveLeaderboard(Leaderboard leaderboard);

    Leaderboard loadLeaderboard(int leaderboardTypeID);
}
