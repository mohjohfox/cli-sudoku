package de.dhbw.karlsruhe.domain.ports.dialogs.output;

import de.dhbw.karlsruhe.domain.models.Leaderboard;
import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.models.LeaderboardType;

import java.util.List;

public interface LeaderboardOutputPort {

    void displayLeaderboardOptions();

    void displayLeaderboard(Leaderboard leaderboard);

    void invalidInput();

    void noLeaderboardDisplayed();

    void noLeaderboardEntriesYet();

    void writeEmptyLine();

    void leaderboardExplanation(LeaderboardType leaderboardType);
}
