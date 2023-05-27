package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;

import java.util.List;

public interface LeaderboardOutputPort {

    void startLeaderboard();

    void displayLeaderboard(List<LeaderboardSaveEntry> leaderboardSaveEntries);

}
