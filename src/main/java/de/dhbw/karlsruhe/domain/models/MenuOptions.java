package de.dhbw.karlsruhe.domain.models;

public enum MenuOptions {
    PLAY("Play"),
    PLAY_SMALL("Play 4x4 Sudoku"),
    SAVED_SUDOKUS("Show saved Sudokus"),
    LEADERBOARD("Leaderboard"),
    SETTINGS("Settings"),
    LOGOUT("Logout");

    final String representation;

    MenuOptions(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}
