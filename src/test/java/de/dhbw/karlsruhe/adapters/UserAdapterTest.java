package de.dhbw.karlsruhe.adapters;

import de.dhbw.karlsruhe.adapters.persistence.UserAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.GameInformation;
import de.dhbw.karlsruhe.domain.models.Setting;
import de.dhbw.karlsruhe.domain.models.User;
import de.dhbw.karlsruhe.domain.ports.persistence.UserPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAdapterTest {

    @BeforeEach
    void createFile() {
        UserPort userPort = new UserAdapter(Location.TEST);
        for (int i = 0; i < 2; i++) {
            User user = createUser(1);
            userPort.saveUser(user);
        }
    }

    @AfterEach
    void deleteFile() {
        try {
            Files.deleteIfExists(Path.of(Location.TEST.getLocation() + "userStoreFile"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void changeUserNameTest() {
        UserPort userPort = new UserAdapter(Location.TEST);
        User user = userPort.getUser("user1");
        GameInformation.username = user.getUserName();
        String newUserName = "MyNewUserName";

        userPort.changeUserName(newUserName);

        User updatedUser = userPort.getUser(newUserName);

        assertEquals(updatedUser.getUserName(), newUserName);
        assertEquals(updatedUser.getSetting(), user.getSetting());
        assertEquals(updatedUser.getPassword(), user.getPassword());
    }

    @Test
    void changeUserNameToForgivenUserNameTest() {
        UserPort userPort = new UserAdapter(Location.TEST);
        User user = userPort.getUser("user1");
        GameInformation.username = user.getUserName();
        String newUserName = "MyNewUserName";

        userPort.changeUserName(newUserName);

        User updatedUser = userPort.getUser(newUserName);

        assertEquals(updatedUser.getUserName(), user.getUserName());
        assertEquals(updatedUser.getSetting(), user.getSetting());
        assertEquals(updatedUser.getPassword(), user.getPassword());
    }

    private User createUser(int id) {
        return new User("user" + id, "password", new Setting(false, false));
    }
}
