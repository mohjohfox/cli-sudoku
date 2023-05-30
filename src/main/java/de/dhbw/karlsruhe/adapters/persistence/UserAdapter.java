package de.dhbw.karlsruhe.adapters.persistence;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.persistence.UserPort;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends AbstractStoreAdapter implements UserPort {

    final String userFileName = "userStoreFile";

    public UserAdapter(Location filePath) {
        super(filePath);
    }

    @Override
    public void save(User user) {

        prepareFileStructure(userFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFullFilePath(userFileName), true))) {
            String formattedEntry = getSaveUserString(user);
            writer.append(formattedEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)));
             BufferedWriter wr = new BufferedWriter(new FileWriter(getFullFilePath(userFileName + ".tmp"), false))) {

            String line;
            while ((line = br.readLine()) != null) {
                String username = getUserNameFromSaveLine(line);
                if (!username.equals(user.getUserName())) {
                    wr.write(line);
                    wr.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        replacingOldFileWithTemporaryFile("User");
    }

    @Override
    public String getPassword(String userName) {
        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)))) {
            String line = br.readLine();

            while (line != null) {
                String userNameInLine = getUserNameFromSaveLine(line);
                String passwordInLine = getPasswordFromSaveLine(line);

                if (userNameInLine.equals(userName)) {
                    return passwordInLine;
                }
                line = br.readLine();
            }
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
                userNames.add(getUserNameFromSaveLine(line));

                line = br.readLine();
            }
        } catch (IOException e) {
            return List.of();
        }
        return userNames;
    }

    @Override
    public User findByUserName(String userName) {
        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)))) {
            String line = br.readLine();

            while (line != null) {
                String username = getUserNameFromSaveLine(line);
                if (!username.equals(userName)) {
                    line = br.readLine();
                    continue;
                }
                String password = getPasswordFromSaveLine(line);
                String[] settings = getSettingsFromSaveLine(line);
                boolean valueHint = Boolean.parseBoolean(settings[0].split("=")[1]);
                boolean fieldValidation = Boolean.parseBoolean(settings[1].split("=")[1]);
                boolean fixMistakes = Boolean.parseBoolean(settings[2].split("=")[1]);
                Setting setting = new Setting(valueHint, fieldValidation, fixMistakes);
                return new User(username, password, setting);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)));
             BufferedWriter wr = new BufferedWriter(new FileWriter(getFullFilePath(userFileName + ".tmp"), false))) {

            String line;
            while ((line = br.readLine()) != null) {
                String username = getUserNameFromSaveLine(line);
                if (username.equals(user.getUserName())) {
                    String updatedLine = getSaveUserString(user);
                    wr.write(updatedLine);
                    wr.newLine();
                } else {
                    wr.write(line);
                    wr.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        replacingOldFileWithTemporaryFile("User");
    }

    public void changeUserName(String newUserName) {
        prepareFileStructure(userFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)));
             BufferedWriter wr = new BufferedWriter(new FileWriter(getFullFilePath(userFileName + ".tmp"), false))) {

            String line;
            while ((line = br.readLine()) != null) {
                String username = getUserNameFromSaveLine(line);
                if (username.equals(GameInformation.username)) {
                    User user = findByUserName(username);
                    user.setUserName(newUserName);
                    GameInformation.username = newUserName;
                    String updatedLine = getSaveUserString(user);
                    wr.write(updatedLine);
                    wr.newLine();
                } else {
                    wr.write(line);
                    wr.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        replacingOldFileWithTemporaryFile("Username");
    }

    public void changePassword(String newPassword) {
        prepareFileStructure(userFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(getFullFilePath(userFileName)));
             BufferedWriter wr = new BufferedWriter(new FileWriter(getFullFilePath(userFileName + ".tmp"), false))) {

            String line;
            while ((line = br.readLine()) != null) {
                String username = getUserNameFromSaveLine(line);
                if (username.equals(GameInformation.username)) {
                    User user = findByUserName(username);
                    user.setPassword(newPassword);
                    String updatedLine = getSaveUserString(user);
                    wr.write(updatedLine);
                    wr.newLine();
                } else {
                    wr.write(line);
                    wr.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        replacingOldFileWithTemporaryFile("Password");
    }

    private String[] getSettingsFromSaveLine(String line) {
        return line.split("&")[2].split(",");
    }

    private String getUserNameFromSaveLine(String line) {
        return line.split("&")[0].split("=")[1];
    }

    private String getPasswordFromSaveLine(String line) {
        return line.split("&")[1].split("=")[1];
    }

    private void replacingOldFileWithTemporaryFile(String changedModifier) {
        File originalFile = new File(getFullFilePath(userFileName));
        try {
            Files.delete(new File(getFullFilePath(userFileName)).toPath());
        } catch (IOException e) {
            System.out.println("File " + getFullFilePath(userFileName) + " could not be deleted.");
        }
        File tempFile = new File(getFullFilePath(userFileName + ".tmp"));
        if (tempFile.renameTo(originalFile)) {
            System.out.println(changedModifier + " changed successfully.");
        } else {
            System.err.println("Failed to update " + changedModifier + ".");
        }
    }

    private String getSaveUserString(User user) {
        return String.format("username=%s&password=%s&%s", user.getUserName(), user.getPassword(), user.getSetting());
    }

}
