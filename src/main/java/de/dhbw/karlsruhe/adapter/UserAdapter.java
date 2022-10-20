package de.dhbw.karlsruhe.adapter;

import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class UserAdapter implements UserPort {

  final String userFile = "src/main/resources/fileStore/userStoreFile";

  @Override
  public void saveUser(User user) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true))) {
      String formattedEntry = String.format("username=%s&password=%s", user.getUserName(), user.getPassword());
      writer.append(formattedEntry);
      writer.newLine();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public String getPassword(String userName) {
    return "";
  }
}
