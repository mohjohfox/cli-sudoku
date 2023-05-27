package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Leaderboard;
import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.LeaderboardStorePort;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class LeaderboardStoreAdapter extends AbstractStoreAdapter implements LeaderboardStorePort {

    private final String LEADERBOARDFILENAME = "LeaderboardStoreFile";

    public LeaderboardStoreAdapter(Location filePath) {
        super(filePath);
    }

    @Override
    public void saveLeaderboard(Leaderboard leaderboard) {
        int leaderboardType = leaderboard.getLeaderboardTypeID();
        String completeFileName = LEADERBOARDFILENAME + leaderboardType;
        List<LeaderboardSaveEntry> leaderboardSaveEntries = leaderboard.getLeaderboardSaveEntries();

        prepareFileStructure(completeFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFullFilePath(completeFileName), true))) {

            for (LeaderboardSaveEntry leaderboardSaveEntry : leaderboardSaveEntries) {
                bufferedWriter.append(leaderboardSaveEntry.getFormattedLeaderboardSaveEntry());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<LeaderboardSaveEntry> loadSavedEntriesFromLeaderboard (int leaderboardTypeID) {
        String completeFileName = LEADERBOARDFILENAME + leaderboardTypeID;
        List<LeaderboardSaveEntry> readLeaderboardSaveEntries = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFullFilePath(completeFileName)))) {
            String line = bufferedReader.readLine();

            readLeaderboardSaveEntries.add(this.parseReadLineToEntry(line));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return readLeaderboardSaveEntries;

    }

    private LeaderboardSaveEntry parseReadLineToEntry(String line) {
        Dictionary tempDict = new Hashtable();
        String[] splitArray = line.split("&");

        for (String s : splitArray) {
            String[] tempSplit = s.split("=");

            tempDict.put(tempSplit[0], tempSplit[1]);
        }

        String saveEntryID = String.valueOf(tempDict.get("SaveID"));
        int leaderboardTypeID = (int) tempDict.get("LeaderboardID");
        String username = String.valueOf(tempDict.get("Username"));
        int score = (int) tempDict.get("Score");
        String date = String.valueOf(tempDict.get("Date"));

        return new LeaderboardSaveEntry(saveEntryID, leaderboardTypeID, username, score, date);
    }

}
