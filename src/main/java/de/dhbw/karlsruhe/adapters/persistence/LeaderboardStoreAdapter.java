package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.Leaderboard;
import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.ports.persistence.LeaderboardStorePort;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LeaderboardStoreAdapter extends AbstractStoreAdapter implements LeaderboardStorePort {

    private final String LEADERBOARDFILENAME = "LeaderboardStoreFile";

    public LeaderboardStoreAdapter(Location filePath) {
        super(filePath);
    }

    @Override
    public void saveLeaderboard(Leaderboard leaderboard) {
        int leaderboardType = leaderboard.getLeaderboardTypeID();
        String newFileName = LEADERBOARDFILENAME + leaderboardType;
        List<LeaderboardSaveEntry> leaderboardSaveEntries = leaderboard.getLeaderboardSaveEntries();

        prepareFileStructure(newFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getFullFilePath(newFileName), true))) {

            for (LeaderboardSaveEntry leaderboardSaveEntry : leaderboardSaveEntries) {
                bufferedWriter.append(leaderboardSaveEntry.getFormattedLeaderboardSaveEntry());
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Leaderboard loadLeaderboard(int leaderboardTypeID) {
        return null;
    }
}
