package de.dhbw.karlsruhe.domain.models;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private final LeaderboardType leaderboardType;
    private List<LeaderboardSaveEntry> leaderboardSaveEntries;

    public Leaderboard(LeaderboardType leaderboardType) {
        this.leaderboardSaveEntries = new ArrayList<>();
        this.leaderboardType = leaderboardType;
    }

    public String getLeaderboardTypeRepresentation() {
        return this.leaderboardType.getRepresentation();
    }

    public int getLeaderboardID() {
        return this.leaderboardType.getTypeID();
    }

    public void updateLeaderboard(int leaderboardID, String username, int score) {
        LeaderboardSaveEntry leaderboardSaveEntry = new LeaderboardSaveEntry(leaderboardID, username, score);

        leaderboardSaveEntries.add(leaderboardSaveEntry);
    }

    public List<LeaderboardSaveEntry> getLeaderboardSaveEntries() {
        return this.leaderboardSaveEntries;
    }
}
