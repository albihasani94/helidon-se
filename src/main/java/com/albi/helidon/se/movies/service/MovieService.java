package com.albi.helidon.se.movies.service;

import com.albi.helidon.se.movies.model.Movie;
import io.helidon.webserver.Handler;
import io.helidon.webserver.Routing;
import io.helidon.webserver.Service;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.util.Collections;
import java.util.List;

public class MovieService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/list", this.getListHandler())
                .get("/test", this.getTestHandler());
    }

    private Handler getListHandler() {
        return (request, response) -> response.send(List.of(
                new Movie("The Dark Knight", 2006),
                new Movie("Man of Steel", 2011),
                new Movie("Shrek", 2003)
                )
        );
    }

    private Handler getTestHandler() {
        return (request, response) -> response.send("test");
    }
}
