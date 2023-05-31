package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.leaderboard.Leaderboard;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.LeaderboardStorePort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    // int leaderboardType = leaderboard.getLeaderboardTypeID();
    String completeFileName = LEADERBOARDFILENAME;
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
  public List<LeaderboardSaveEntry> loadSavedEntriesFromLeaderboard(int leaderboardTypeID) {
    String completeFileName = LEADERBOARDFILENAME;
    List<LeaderboardSaveEntry> readLeaderboardSaveEntries = new ArrayList<>();
    List<LeaderboardSaveEntry> readLeaderboardSaveEntriesForID;

    if (!this.fileIsAvailable(completeFileName)) {

      return readLeaderboardSaveEntries;
    }

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFullFilePath(completeFileName)))) {
      String readLine = "";

      while ((readLine = bufferedReader.readLine()) != null) {
        readLeaderboardSaveEntries.add(this.parseReadLineToEntry(readLine));
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    readLeaderboardSaveEntriesForID = this.filterReadLeaderboardSaveEntries(readLeaderboardSaveEntries,
        leaderboardTypeID);

    return readLeaderboardSaveEntriesForID;

  }

  private List<LeaderboardSaveEntry> filterReadLeaderboardSaveEntries(
      List<LeaderboardSaveEntry> readLeaderboardSaveEntries, int leaderboardTypeID) {
    List<LeaderboardSaveEntry> readLeaderboardSaveEntriesForID = new ArrayList<>();

    // Filter for relevant entries
    for (LeaderboardSaveEntry leaderboardSaveEntry : readLeaderboardSaveEntries) {
      if (leaderboardSaveEntry.getLeaderboardTypeID() == leaderboardTypeID) {
        readLeaderboardSaveEntriesForID.add(leaderboardSaveEntry);
      }
    }

    return readLeaderboardSaveEntriesForID;
  }

  private boolean fileIsAvailable(String completeFileName) {
    boolean fileIsAvailable = false;

    File f = new File(getFullFilePath(completeFileName));
    if (f.exists() && !f.isDirectory()) {
      fileIsAvailable = true;
    }

    return fileIsAvailable;
  }

  private LeaderboardSaveEntry parseReadLineToEntry(String line) {
    Dictionary tempDict = new Hashtable();
    String[] splitArray = line.split("&");

    for (String s : splitArray) {
      String[] tempSplit = s.split("=");

      tempDict.put(tempSplit[0], tempSplit[1]);
    }

    String saveEntryID = String.valueOf(tempDict.get("SaveID"));
    int leaderboardTypeID = Integer.parseInt((String) tempDict.get("LeaderboardID"));
    String username = String.valueOf(tempDict.get("Username"));
    int score = Integer.parseInt((String) tempDict.get("Score"));
    String date = String.valueOf(tempDict.get("Date"));

    return new LeaderboardSaveEntry(saveEntryID, leaderboardTypeID, username, score, date);
  }

}
