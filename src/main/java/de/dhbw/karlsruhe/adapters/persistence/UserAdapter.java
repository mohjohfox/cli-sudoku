package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.persistence.UserPort;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends AbstractStoreAdapter implements UserPort {

  final String userFileName = "userStoreFile";

  public UserAdapter(Location filePath) {
    super(filePath);
  }

  @Override
  public void saveUser(User user) {

    prepareFileStructure(userFileName);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(userFileName), true))) {
      String formattedEntry = String.format("username=%s&password=%s", user.getUserName(), user.getPassword());
      writer.append(formattedEntry);
      writer.newLine();
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public String getPassword(String userName) {
    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)))) {
      String line = br.readLine();

      while (line != null) {
        String[] array = line.split("&");
        String userNameInLine = array[0].split("=")[1];
        String passwordInLine = array[1].split("=")[1];

        if (userNameInLine.equals(userName)) {
          return passwordInLine;
        }
        line = br.readLine();
      }
      System.out.println("Username not found!");
    } catch (IOException e) {
      return "";
    }
    return "";
  }

  @Override
  public List<String> getAllUserNames() {

    List<String> userNames = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)))) {
      String line = br.readLine();

      while (line != null) {
        userNames.add(line.split("&")[0].split("=")[1]);

        line = br.readLine();
      }
    } catch (IOException e) {
      return List.of();
    }
    return userNames;
  }
}