package services.rest;

import services.GithubService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class RestInitializer extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public RestInitializer() {
        singletons.add(new GithubService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
