package de.dhbw.karlsruhe.domain.ports;

import de.dhbw.karlsruhe.domain.models.User;
import java.util.List;

public interface UserPort {

  void saveUser(User user);

  String getPassword(String userName);

  List<String> getAllUserNames();
}
