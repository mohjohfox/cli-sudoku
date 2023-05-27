package de.dhbw.karlsruhe.domain.models;

import java.util.Date;
import java.util.UUID;

public class LeaderboardSaveEntry {

    private UUID saveEntryID;
    private int leaderBoardTypeID;
    private String username;
    private int score;
    private Date date;

    public LeaderboardSaveEntry(int leaderBoardTypeID, String username, int score) {
        this.saveEntryID = UUID.randomUUID();
        this.leaderBoardTypeID = leaderBoardTypeID;
        this.username = username;
        this.score = score;
        this.date = new Date();
    }

    public String getFormattedLeaderboardSaveEntry() {
        return String.format("SaveID=%s&LeaderboardID=%s&Username=%s&Score=%s&Date=%s",
                this.saveEntryID, this.leaderBoardTypeID, this.username, this.score, this.date.toString());
    }

}
