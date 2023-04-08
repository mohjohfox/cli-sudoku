package de.dhbw.karlsruhe.adapters;

import de.dhbw.karlsruhe.domain.Location;

import java.io.File;
import java.io.IOException;

public abstract class AbstractStoreAdapter {

  final Location userFilePath;

  public AbstractStoreAdapter(Location filePath) {
    this.userFilePath = filePath;
  }

  public void prepareFileStructure(String fileName) {
    File userFile = new File(userFilePath.getLocation() + fileName);

    try {
      userFile.createNewFile();
    } catch (IOException e) {
      userFile.getParentFile().mkdirs();
    }
  }

  public String getFullFilePath(String fileName) {
    return userFilePath.getLocation() + fileName;
  }

}
