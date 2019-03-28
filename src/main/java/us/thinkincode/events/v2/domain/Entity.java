package us.thinkincode.events.v2.domain;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private String id;
    private List<Event> events;

    public Entity() {

    }

    public Entity(String id, String name) {
        this.name = name;
        this.id = id;
    }

    public void addEvent(Event event) {
        if (events == null) events = new ArrayList<>();

        this.events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
