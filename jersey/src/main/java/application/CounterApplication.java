package application;

import jakarta.ws.rs.core.Application;
import resource.CounterResource;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CounterApplication extends Application {
    private final Set<Object> singletons = new HashSet<>();

    public CounterApplication() {
        singletons.add(new CounterResource());
    }

    public Set<Class<?>> getClasses() {
        return Collections.emptySet();
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
