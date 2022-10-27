package de.dhbw.karlsruhe.adapter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;

public class UserAdapter implements UserPort {

	final String userFilePath = "src/main/resources/fileStore/userStoreFile";

	@Override
	public void saveUser(User user) {

		prepareFileStructure();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath, true))) {
			String formattedEntry = String.format("username=%s&password=%s", user.getUserName(), user.getPassword());
			writer.append(formattedEntry);
			writer.newLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public String getPassword(String userName) {
		try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
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

		try (BufferedReader br = new BufferedReader(new FileReader(userFilePath))) {
			String line = br.readLine();

			while (line != null) {
				userNames.add(line.split("&")[0].split("=")[1]);

				line = br.readLine();
			}
		} catch (IOException e) {
			List.of();
		}
		return userNames;
	}

	private void prepareFileStructure() {
		File userFile = new File(userFilePath);

		try {
			userFile.createNewFile();
		} catch (IOException e) {
			userFile.getParentFile().mkdirs();
		}
	}

}
