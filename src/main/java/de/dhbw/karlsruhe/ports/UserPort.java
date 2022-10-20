package de.dhbw.karlsruhe.ports;

import de.dhbw.karlsruhe.model.User;

public interface UserPort {

  void saveUser(User user);

  String getPassword(String userName);

}
