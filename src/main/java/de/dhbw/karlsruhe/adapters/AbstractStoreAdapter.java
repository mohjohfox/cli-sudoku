package de.dhbw.karlsruhe.adapters;

import java.io.File;
import java.io.IOException;

public abstract class AbstractStoreAdapter {

  final String userFilePath = "src/main/resources/fileStore/";

  public void prepareFileStructure(String fileName) {
    File userFile = new File(userFilePath + fileName);

    try {
      userFile.createNewFile();
    } catch (IOException e) {
      userFile.getParentFile().mkdirs();
    }
  }

  public String getFullFilePath(String fileName) {
    return userFilePath + fileName;
  }

}
