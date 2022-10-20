package de.dhbw.karlsruhe.adapter;

import de.dhbw.karlsruhe.model.User;
import de.dhbw.karlsruhe.ports.UserPort;

public class UserAdapter implements UserPort {

  @Override
  public String saveUser(User user) {
    return null;
  }

  @Override
  public String getPassword(String userName) {
    return null;
  }
}
