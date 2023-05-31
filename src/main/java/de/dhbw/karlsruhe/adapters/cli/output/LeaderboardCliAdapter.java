package de.dhbw.karlsruhe.adapters.cli.output;

import de.dhbw.karlsruhe.domain.models.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.models.LeaderboardType;
import de.dhbw.karlsruhe.domain.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.domain.services.DependencyFactory;

import java.util.List;

public class LeaderboardCliAdapter implements LeaderboardOutputPort {

    private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

    @Override
    public void displayLeaderboardOptions() {
        cliOutputPort.write("Please select a leaderboard:");

        cliOutputPort.write("[" + LeaderboardType.COMPLETE.getTypeID() + "] " + LeaderboardType.COMPLETE.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.SOLVINGTIME.getTypeID() + "] " + LeaderboardType.SOLVINGTIME.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_EASY.getTypeID() + "] " + LeaderboardType.DIFFICULTY_EASY.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_MEDIUM.getTypeID() + "] " + LeaderboardType.DIFFICULTY_MEDIUM.getRepresentation());
        cliOutputPort.write("[" + LeaderboardType.DIFFICULTY_HARD.getTypeID() + "] " + LeaderboardType.DIFFICULTY_HARD.getRepresentation());
    }

    @Override
    public void displayLeaderboard(String leaderboardTypeRepresentation, List<LeaderboardSaveEntry> leaderboardSaveEntries) {
        int rank = 1;

        StringBuilder leaderboardDialog = new StringBuilder(leaderboardTypeRepresentation + " Leaderboard:");
        leaderboardDialog.append(System.getProperty("line.separator"));
        leaderboardDialog.append(System.getProperty("line.separator"));

        leaderboardDialog.append("\tScore\tUsername\tDate");
        leaderboardDialog.append(System.getProperty("line.separator"));
        leaderboardDialog.append("-------------------------------------------------------");
        leaderboardDialog.append(System.getProperty("line.separator"));

        for (LeaderboardSaveEntry leaderboardSaveEntry : leaderboardSaveEntries) {
            leaderboardDialog.append(rank + "\t" + leaderboardSaveEntry.getLeaderboardSaveEntryToDisplay());
            leaderboardDialog.append(System.getProperty("line.separator"));

            rank++;
        }

        cliOutputPort.write(leaderboardDialog.toString());

    }

    @Override
    public void invalidInput() {
        cliOutputPort.write("This isn't a valid option!");
    }

    @Override
    public void noLeaderboardDisplayed() {
        cliOutputPort.write("No Leaderboard displayed!");
    }

    @Override
    public void noLeaderboardEntriesYet() {
        cliOutputPort.write("No Leaderboard entries yet!");
    }

    @Override
    public void writeEmptyLine() {
        cliOutputPort.writeEmptyLine();
    }

    @Override
    public void leaderboardExplanation(LeaderboardType leaderboardType) {
        cliOutputPort.write("Subsequent is a short explanation for entries of the \"" + leaderboardType.getRepresentation() + "\" Leaderboard and how the score is calculated:");
        cliOutputPort.writeEmptyLine();

        switch (leaderboardType.getTypeID()) {
            case 1:
                cliOutputPort.write("In the \"" + leaderboardType.getRepresentation() + "\" Leaderboard all possible metrics are considered.");
                cliOutputPort.write("All possible metrics are considered such as validity, duration and difficulty.");
                cliOutputPort.write("The score is calculated according to weighted values of the considered metrics validity, duration and difficulty.");
                break;
            case 2:
                cliOutputPort.write("In the \"" + leaderboardType.getRepresentation() + "\" Leaderboard the duration is considered.");
                cliOutputPort.write("The score is calculated according to weighted values of the duration and validity of the Sudoku.");
                break;
            case 3:
                cliOutputPort.write("The \"" + leaderboardType.getRepresentation() + "\" Leaderboard displays only scores of Sudokus for the difficulty easy.");
                cliOutputPort.write("Therefore the validity and the difficulty is considered.");
                cliOutputPort.write("The score is calculated according to the weighted metrics validity and difficulty.");
                break;
            case 4:
                cliOutputPort.write("The \"" + leaderboardType.getRepresentation() + "\" Leaderboard displays only scores of Sudokus for the difficulty medium.");
                cliOutputPort.write("Therefore the validity and the difficulty is considered.");
                cliOutputPort.write("The score is calculated according to the weighted metrics validity and difficulty.");
                cliOutputPort.write("The score here is usually higher, because the difficulty gets more basic points.");
                break;
            case 5:
                cliOutputPort.write("The \"" + leaderboardType.getRepresentation() + "\" Leaderboard displays only scores of Sudokus for the difficulty hard.");
                cliOutputPort.write("Therefore the validity and the difficulty is considered.");
                cliOutputPort.write("The score is calculated according to the weighted metrics validity and difficulty.");
                cliOutputPort.write("The score here is usually higher than for difficulty easy or medium.");
                break;
            default:

        }
    }
}
