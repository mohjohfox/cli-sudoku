package de.dhbw.karlsruhe.domain.services;

import java.util.ArrayList;
import java.util.List;

public class DependencyFactory {

    private static DependencyFactory instance;

    private final List<Object> dependencies = new ArrayList<>();

    public static DependencyFactory getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new DependencyFactory();
        return instance;
    }

    public void RegisterDependency(Object o) {
        dependencies.add(o);
    }

    public <T> T getDependency(Class<T> type) {
        for (Object o : dependencies) {
            if (type.isInstance(o)) {
                return (T) o;
            }
        }
        throw new RuntimeException("No dependency of type " + type.getName() + " found.");
    }
}
