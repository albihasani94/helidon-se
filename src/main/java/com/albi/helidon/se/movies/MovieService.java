package com.albi.helidon.se.movies;

import io.helidon.webserver.Routing;
import io.helidon.webserver.Service;

import java.util.List;

public class MovieService implements Service {

    private static final List<String> MOVIES = List.of("Batman", "Man of Steel", "Shrek");

    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/list", (serverRequest, serverResponse) -> serverResponse.send(MOVIES))
                .get("/test", (serverRequest, serverResponse) -> serverResponse.send("test"));
    }
}
