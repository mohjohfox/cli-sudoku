package de.dhbw.karlsruhe.domain;

public enum Location {

    PROD("src/main/resources/fileStore/"),
    TEST("src/test/resources/fileStore/");

    private String location;

    Location(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
