package de.dhbw.karlsruhe.mocks;

import de.dhbw.karlsruhe.adapters.persistence.UserAdapter;
import de.dhbw.karlsruhe.domain.Location;
import de.dhbw.karlsruhe.domain.models.User;

public class MockUserAdapter extends UserAdapter {

    public MockUserAdapter(Location filePath) {
        super(filePath);
    }

    @Override
    public void save(User user){
    }
}
