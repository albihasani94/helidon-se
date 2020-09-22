package com.albi.helidon.se.movies.service;

import com.albi.helidon.se.movies.model.Movie;
import com.albi.helidon.se.movies.model.MovieNotFound;
import io.helidon.common.http.Http;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;
import io.helidon.webserver.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MovieService implements Service {

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
