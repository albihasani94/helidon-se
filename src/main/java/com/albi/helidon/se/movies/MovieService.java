package com.albi.helidon.se.movies;

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
        return (request, response) -> response.send(
                JSON.createArrayBuilder(
                        List.of("Batman", "Man of Steel", "Shrek"))
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
