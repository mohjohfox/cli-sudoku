package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.DurationTrackSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.DurationTrackPort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DurationTrackAdapter extends AbstractStoreAdapter implements DurationTrackPort {

    final String durationTrackFileName = "durationTrackFile";

    public DurationTrackAdapter(Location filePath) {
        super(filePath);
    }

    @Override
    public void saveSolvingTime(DurationTrackSaveEntry durationTrackSaveEntry) {
        prepareFileStructure(durationTrackFileName);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(durationTrackFileName), true))) {
            String durationToWriteFormatted = String.format("id=%s&sudoku=%s&duration=%s",
                    durationTrackSaveEntry.getSaveId(), durationTrackSaveEntry.getSudoku(), durationTrackSaveEntry.getTime());

            writer.append(durationToWriteFormatted);
            writer.newLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
