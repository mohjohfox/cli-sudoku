package de.dhbw.karlsruhe.domain.models;

public enum MenuOptions {
    PLAY("Play"),
    PLAY_SMALL("Play 4x4 Sudoku"),
    PLAY_BIG("Play 16x16 Sudoku"),
    SAVED_SUDOKUS("Show saved Sudokus"),
    LEADERBOARD("Leaderboard"),
    SETTINGS("Settings"),
    TUTORIAL("Tutorial"),
    RULES("Sudoku rules"),
    ARCADE("Arcade math game"),
    LOGOUT("Logout");

    final String representation;

    MenuOptions(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
