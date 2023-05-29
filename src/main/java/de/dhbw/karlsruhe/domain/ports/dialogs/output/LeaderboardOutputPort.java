package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;

import java.util.List;

public interface LeaderboardOutputPort {

    void displayLeaderboardOptions();

    void displayLeaderboard(String leaderboardTypeRepresentation, List<LeaderboardSaveEntry> leaderboardSaveEntries);

    void invalidInput();

    void noLeaderboardDisplayed();

    void noLeaderboardEntriesYet();

    void writeEmptyLine();
}
