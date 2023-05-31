package de.dhbw.karlsruhe.domain.models.leaderboard;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

  private LeaderboardType leaderboardType;
  private List<LeaderboardSaveEntry> leaderboardSaveEntries;

  public Leaderboard() {
    this.leaderboardSaveEntries = new ArrayList<>();
  }

  public Leaderboard(LeaderboardType leaderboardType, List<LeaderboardSaveEntry> leaderboardSaveEntries) {
    this.leaderboardType = leaderboardType;
    this.leaderboardSaveEntries = leaderboardSaveEntries;
  }

  public String getLeaderboardTypeRepresentation() {
    return this.leaderboardType.getRepresentation();
  }

  public int getLeaderboardTypeID() {
    return this.leaderboardType.getTypeID();
  }

  public void addToLeaderboard(int leaderboardID, String username, int score) {
    LeaderboardSaveEntry leaderboardSaveEntry = new LeaderboardSaveEntry(leaderboardID, username, score);

    leaderboardSaveEntries.add(leaderboardSaveEntry);
  }

  public List<LeaderboardSaveEntry> getLeaderboardSaveEntries() {
    return this.leaderboardSaveEntries;
  }

  public void setLeaderboardType(LeaderboardType leaderboardType) {
    this.leaderboardType = leaderboardType;
  }

  public LeaderboardType getLeaderboardType() {
    return this.leaderboardType;
  }
}
