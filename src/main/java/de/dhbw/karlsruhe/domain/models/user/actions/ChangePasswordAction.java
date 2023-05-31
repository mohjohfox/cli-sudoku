package de.dhbw.karlsruhe.domain.models.user.actions;

import de.dhbw.karlsruhe.application.services.DependencyFactory;
import de.dhbw.karlsruhe.application.services.UserService;
import de.dhbw.karlsruhe.domain.models.core.User;

public class ChangePasswordAction extends UserAction {

  private final UserService userService = DependencyFactory.getInstance().getDependency(UserService.class);
  private final String newPassword;

  public ChangePasswordAction(String newPassword) {
    this.newPassword = newPassword;
  }

  @Override
  public void executeAction(User user) {
    userService.changePassword(newPassword);
  }

}
