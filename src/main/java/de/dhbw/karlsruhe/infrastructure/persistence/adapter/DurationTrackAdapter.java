package de.dhbw.karlsruhe.infrastructure.persistence.adapter;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;
import de.dhbw.karlsruhe.domain.wrappers.TimeWrapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class DurationTrackAdapter extends AbstractStoreAdapter {

  private final String DURATIONTRACKFILENAME = "durationTrackFile";

  public DurationTrackAdapter(Location filePath) {
    super(filePath);
  }

  public void saveSolvingTime(DurationTrackSaveEntry durationTrackSaveEntry) {
    prepareFileStructure(DURATIONTRACKFILENAME);

    try (BufferedWriter bufferedWriter = new BufferedWriter(
        new FileWriter(getFullFilePath(DURATIONTRACKFILENAME), true))) {
      String durationToWriteFormatted = String.format("id=%s&sudoku=%s&duration=%s",
          durationTrackSaveEntry.getSaveId(), durationTrackSaveEntry.getSudokuId(),
          durationTrackSaveEntry.getTimeAsString());

      bufferedWriter.append(durationToWriteFormatted);
      bufferedWriter.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public long loadSolvingTime(String sudokuId) {
    prepareFileStructure(DURATIONTRACKFILENAME);
    List<DurationTrackSaveEntry> trackSaveEntryList = new ArrayList<>();
    long solvingTime = 0;

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFullFilePath(DURATIONTRACKFILENAME)))) {
      String line = bufferedReader.readLine();
      if (line != null) {
        trackSaveEntryList.add(this.parseReadLineToEntry(line));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    for (DurationTrackSaveEntry durationTrackSaveEntry : trackSaveEntryList) {
      String entrySudokuId = durationTrackSaveEntry.getSudokuId();

      if (entrySudokuId.equals(sudokuId)) {
        solvingTime += durationTrackSaveEntry.getDuration();
      }
    }

    return solvingTime;
  }

  private DurationTrackSaveEntry parseReadLineToEntry(String line) {
    Dictionary tempDict = new Hashtable();
    String[] splitArray = line.split("&");

    for (String s : splitArray) {
      String[] tempSplit = s.split("=");

      tempDict.put(tempSplit[0], tempSplit[1]);
    }

    String saveId = String.valueOf(tempDict.get("id"));
    String sudokuId = String.valueOf(tempDict.get("sudoku"));
    String durationRaw = String.valueOf(tempDict.get("duration"));
    long durationMillis = this.parseDurationFormatted(durationRaw);

    return new DurationTrackSaveEntry(saveId, sudokuId, durationMillis);
  }

  private long parseDurationFormatted(String durationRaw) {
    TimeWrapper timeWrapper = new TimeWrapper(durationRaw);

    return timeWrapper.getTimeAsLong();
  }
}
