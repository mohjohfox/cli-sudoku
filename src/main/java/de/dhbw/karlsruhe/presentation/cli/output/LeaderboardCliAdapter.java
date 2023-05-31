package de.dhbw.karlsruhe.presentation.cli.output;

import de.dhbw.karlsruhe.application.ports.dialogs.output.LeaderboardOutputPort;
import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.domain.models.leaderboard.Leaderboard;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardSaveEntry;
import de.dhbw.karlsruhe.domain.models.leaderboard.LeaderboardType;
import java.util.List;

public class LeaderboardCliAdapter implements LeaderboardOutputPort {

  private final CliOutputPort cliOutputPort = DependencyFactory.getInstance().getDependency(CliOutputPort.class);

  @Override
  public void displayLeaderboardOptions() {
    cliOutputPort.writeLine("Please select a leaderboard:");

    cliOutputPort.writeLine(
        "[" + LeaderboardType.COMPLETE.getTypeID() + "] " + LeaderboardType.COMPLETE.getRepresentation());
    cliOutputPort.writeLine(
        "[" + LeaderboardType.SOLVINGTIME.getTypeID() + "] " + LeaderboardType.SOLVINGTIME.getRepresentation());
    cliOutputPort.writeLine(
        "[" + LeaderboardType.DIFFICULTY_EASY.getTypeID() + "] " + LeaderboardType.DIFFICULTY_EASY.getRepresentation());
    cliOutputPort.writeLine("[" + LeaderboardType.DIFFICULTY_MEDIUM.getTypeID() + "] "
        + LeaderboardType.DIFFICULTY_MEDIUM.getRepresentation());
    cliOutputPort.writeLine(
        "[" + LeaderboardType.DIFFICULTY_HARD.getTypeID() + "] " + LeaderboardType.DIFFICULTY_HARD.getRepresentation());
  }

  @Override
  public void displayLeaderboard(Leaderboard leaderboard) {
    int rank = 1;
    LeaderboardType leaderboardType = leaderboard.getLeaderboardType();
    List<LeaderboardSaveEntry> leaderboardSaveEntries = leaderboard.getLeaderboardSaveEntries();

    StringBuilder leaderboardDialog = new StringBuilder(leaderboardType.getRepresentation() + " Leaderboard:");
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

    cliOutputPort.writeLine(leaderboardDialog.toString());

  }

  @Override
  public void invalidInput() {
    cliOutputPort.writeLine("This isn't a valid option!");
  }

  @Override
  public void noLeaderboardDisplayed() {
    cliOutputPort.writeLine("No Leaderboard displayed!");
  }

  @Override
  public void noLeaderboardEntriesYet() {
    cliOutputPort.writeLine("No Leaderboard entries yet!");
  }

  @Override
  public void writeEmptyLine() {
    cliOutputPort.writeEmptyLine();
  }

  @Override
  public void leaderboardExplanation(LeaderboardType leaderboardType) {
    cliOutputPort.writeLine(
        "Subsequent is a short explanation for entries of the \"" + leaderboardType.getRepresentation()
            + "\" Leaderboard and how the score is calculated:");
    cliOutputPort.writeEmptyLine();

    switch (leaderboardType.getTypeID()) {
      case 1:
        cliOutputPort.writeLine(
            "In the \"" + leaderboardType.getRepresentation() + "\" Leaderboard all possible metrics are considered.");
        cliOutputPort.writeLine("All possible metrics are considered such as validity, duration and difficulty.");
        cliOutputPort.writeLine(
            "The score is calculated according to weighted values of the considered metrics validity, duration and difficulty.");
        break;
      case 2:
        cliOutputPort.writeLine(
            "In the \"" + leaderboardType.getRepresentation() + "\" Leaderboard the duration is considered.");
        cliOutputPort.writeLine(
            "The score is calculated according to weighted values of the duration and validity of the Sudoku.");
        break;
      case 3:
        cliOutputPort.writeLine("The \"" + leaderboardType.getRepresentation()
            + "\" Leaderboard displays only scores of Sudokus for the difficulty easy.");
        cliOutputPort.writeLine("Therefore the validity and the difficulty is considered.");
        cliOutputPort.writeLine("The score is calculated according to the weighted metrics validity and difficulty.");
        break;
      case 4:
        cliOutputPort.writeLine("The \"" + leaderboardType.getRepresentation()
            + "\" Leaderboard displays only scores of Sudokus for the difficulty medium.");
        cliOutputPort.writeLine("Therefore the validity and the difficulty is considered.");
        cliOutputPort.writeLine("The score is calculated according to the weighted metrics validity and difficulty.");
        cliOutputPort.writeLine("The score here is usually higher, because the difficulty gets more basic points.");
        break;
      case 5:
        cliOutputPort.writeLine("The \"" + leaderboardType.getRepresentation()
            + "\" Leaderboard displays only scores of Sudokus for the difficulty hard.");
        cliOutputPort.writeLine("Therefore the validity and the difficulty is considered.");
        cliOutputPort.writeLine("The score is calculated according to the weighted metrics validity and difficulty.");
        cliOutputPort.writeLine("The score here is usually higher than for difficulty easy or medium.");
        break;
      default:

    }
  }

  @Override
  public void displayLeaderboardIntroduction() {
    cliOutputPort.writeLine("Depending on which kind of Leaderboard you select, the people to compete with differ.");
    cliOutputPort.writeLine(
        "After choosing one of the option a short explanation about metrics and competitors will be displayed.");
  }
}
