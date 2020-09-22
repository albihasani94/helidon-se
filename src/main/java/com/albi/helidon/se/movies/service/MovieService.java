package com.albi.helidon.se.movies.service;

import com.albi.helidon.se.movies.exception.MovieNotFound;
import com.albi.helidon.se.movies.model.Movie;
import io.helidon.common.http.Http;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MovieService implements Service {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Override
    public void update(Routing.Rules rules) {
        rules
                .get("/list", this::getListHandler)
                .get("/list/flawed", this::getFlawedListHandler)
                .get("/test", this::getTestHandler);
    }

    private void getListHandler(ServerRequest request, ServerResponse response) {
        response.send(List.of(
                new Movie("The Dark Knight", 2006),
                new Movie("Man of Steel", 2011),
                new Movie("Shrek", 2003)
                )
        );
    }

    private void getFlawedListHandler(ServerRequest request, ServerResponse response) {
        this.getFlawedList()
                .thenAccept(response::send)
                .exceptionally(error -> {
                    response.status(Http.Status.NOT_FOUND_404);
                    response.send(
                            new MovieNotFound("Could not get movies", LocalDateTime.now().toString())
                    );
                    return null;
                });
    }

    private CompletableFuture<List<Movie>> getFlawedList() {
        return CompletableFuture.failedFuture(new RuntimeException("Sorry movies are closed"));
    }

    private void getTestHandler(ServerRequest request, ServerResponse response) {
        response.send("test");
    }
}
