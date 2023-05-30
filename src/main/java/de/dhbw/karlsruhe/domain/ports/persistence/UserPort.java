package de.dhbw.karlsruhe.domain.ports.persistence;

import de.dhbw.karlsruhe.domain.models.User;

import java.util.List;

public interface UserPort {

  void save(User user);

  void delete(User user);

  String getPassword(String userName);

  List<String> getAllUserNames();

  User findByUserName(String userName);

  void updateUser(User user);

  void changeUserName(String newUserName);

  void changePassword(String newPassword);

}
