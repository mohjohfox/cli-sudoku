package de.dhbw.karlsruhe.domain.models;

import java.util.Date;
import java.util.UUID;

public class LeaderboardSaveEntry {

    private UUID saveEntryID;
    private int leaderBoardTypeID;
    private String username;
    private int score;
    private Date date;
    private String dateAsString;

    public LeaderboardSaveEntry(int leaderBoardTypeID, String username, int score) {
        this.saveEntryID = UUID.randomUUID();
        this.leaderBoardTypeID = leaderBoardTypeID;
        this.username = username;
        this.score = score;
        this.date = new Date();
        this.dateAsString = this.date.toString();
    }

    public LeaderboardSaveEntry(String saveEntryID, int leaderBoardTypeID, String username, int score, String date) {
        this.saveEntryID = UUID.fromString(saveEntryID);
        this.leaderBoardTypeID = leaderBoardTypeID;
        this.username = username;
        this.score = score;
        this.date = new Date(date);
        this.dateAsString = date;
    }

    public String getFormattedLeaderboardSaveEntry() {
        return String.format("SaveID=%s&LeaderboardID=%s&Username=%s&Score=%s&Date=%s",
                this.getSaveEntryID(), this.getLeaderBoardTypeID(), this.getUsername(), this.getScore(), this.getDateAsString());
    }

    public UUID getSaveEntryID() {
        return saveEntryID;
    }

    public int getLeaderBoardTypeID() {
        return leaderBoardTypeID;
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
