package us.thinkincode.events.v1.domain;

import java.time.LocalDateTime;

public class CreatedObj {

    private String byUsername;
    private LocalDateTime date;

    public CreatedObj() {
    }

    public CreatedObj(String byUsername, LocalDateTime date) {
        this.byUsername = byUsername;
        this.date = date;
    }

    public String getByUsername() {
        return byUsername;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
