package de.dhbw.karlsruhe.mocks;

import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.core.User;
import de.dhbw.karlsruhe.infrastructure.persistence.adapter.UserAdapter;

public class MockUserAdapter extends UserAdapter {

  public MockUserAdapter(Location filePath) {
    super(filePath);
  }

  @Override
  public void save(User user) {
  }
}
