package com.albi.helidon.se.movies.model;

public class MovieNotFound {
    String message;
    String incidentTime;

    public MovieNotFound(String message, String incidentTime) {
        this.message = message;
        this.incidentTime = incidentTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }
}
