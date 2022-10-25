package de.dhbw.karlsruhe.services;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.dhbw.karlsruhe.adapter.UserAdapter;
import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;

public class UserService {

	private final EncryptionService encryptionService = new EncryptionService();
	private final UserPort userPort = new UserAdapter();

	public void saveUser(User user) {
		userPort.saveUser(user);
	}

	public boolean createUser(User createUser) throws NoSuchAlgorithmException {

		if (isUserNameFree(createUser.getUserName()) && isPasswordNotEmpty(createUser.getPassword())
				&& isUserNameWithoutSpecialCharacters(createUser.getUserName())
				&& isPasswordWithoutSpecialCharacters(createUser.getPassword())) {
			String encryptPassword = encryptionService.getSHAEncryptedPassword(createUser.getPassword());
			saveUser(new User(createUser.getUserName(), encryptPassword));
			return true;
		} else {
			System.err.println("Username is already assigned or username / password contains forbidden characters!");
			return false;
		}

	}

	public boolean isPasswordCorrect(User loginUser) throws NoSuchAlgorithmException {
		return !getPasswordFromUserName(loginUser.getUserName()).isBlank()
				&& encryptionService.getSHAEncryptedPassword(loginUser.getPassword())
						.equals(getPasswordFromUserName(loginUser.getUserName()));
	}

	private String getPasswordFromUserName(String userName) {
		return userPort.getPassword(userName);
	}

	private boolean isUserNameFree(String userName) {
		return !userPort.getAllUserNames().contains(userName);
	}

	private boolean isUserNameWithoutSpecialCharacters(String userName) {
		Pattern p = Pattern.compile("[^a-zA-Z0-9]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(userName);
		return !m.find();
	}

	private boolean isPasswordWithoutSpecialCharacters(String password) {
		Pattern p = Pattern.compile("&=", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(password);
		return !m.find();
	}

	private boolean isPasswordNotEmpty(String password) {
		return !password.isBlank();
	}

}
