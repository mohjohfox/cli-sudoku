package de.dhbw.karlsruhe.domain.models;

import java.util.Date;
import java.util.UUID;

public class LeaderboardSaveEntry {

    private UUID saveEntryID;
    private int leaderboardTypeID;
    private String username;
    private int score;
    private Date date;
    private String dateAsString;

    public LeaderboardSaveEntry(int leaderboardTypeID, String username, int score) {
        this.saveEntryID = UUID.randomUUID();
        this.leaderboardTypeID = leaderboardTypeID;
        this.username = username;
        this.score = score;
        this.date = new Date();
        this.dateAsString = this.date.toString();
    }

    public LeaderboardSaveEntry(String saveEntryID, int leaderboardTypeID, String username, int score, String date) {
        this.saveEntryID = UUID.fromString(saveEntryID);
        this.leaderboardTypeID = leaderboardTypeID;
        this.username = username;
        this.score = score;
        // this.date = new Date(date);
        this.dateAsString = date;
    }

    public String getFormattedLeaderboardSaveEntry() {
        return String.format("SaveID=%s&LeaderboardID=%s&Username=%s&Score=%s&Date=%s",
                this.getSaveEntryID(), this.getLeaderboardTypeID(), this.getUsername(), this.getScore(), this.getDateAsString());
    }

    public String getLeaderboardSaveEntryToDisplay () {
        return String.format("%s\t%s\t%s", this.getScore(), this.getUsername(), this.getDateAsString());
    }

    public UUID getSaveEntryID() {
        return saveEntryID;
    }

    public int getLeaderboardTypeID() {
        return leaderboardTypeID;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        return dateAsString;
    }
}
