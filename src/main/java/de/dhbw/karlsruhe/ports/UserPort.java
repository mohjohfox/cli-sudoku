package de.dhbw.karlsruhe.ports;

import de.dhbw.karlsruhe.models.User;
import java.util.List;

public interface UserPort {

  void saveUser(User user);

  String getPassword(String userName);

  List<String> getAllUserNames();
}
