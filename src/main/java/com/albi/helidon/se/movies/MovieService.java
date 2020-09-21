package com.albi.helidon.se.movies;

import io.helidon.webserver.*;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class MovieService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private static final List<String> MOVIES = List.of("Batman", "Man of Steel", "Shrek");

    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/list", this.getListHandler())
                .get("/test", this.getTestHandler());
    }

    private Handler getListHandler() {
        return (request, response) -> response.send(
                JSON.createArrayBuilder(MOVIES)
                        .build()
        );
    }

    private Handler getTestHandler() {
        return (request, response) -> response.send(
                JSON.createObjectBuilder()
                        .add("message", "test")
                        .build());
    }
}
