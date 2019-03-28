package us.thinkincode.events.v3.domain;

import java.time.LocalDateTime;

public class Entity {

    private String name;
    private String id;
    private CreatedObj created;

    public Entity() {

    }

    public Entity(String id, String name, String createdByUsername, LocalDateTime dtCreated) {
        this.name = name;
        this.id = id;
        this.created =  new CreatedObj(createdByUsername, dtCreated);
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

    public void setCreated(CreatedObj created) {
        this.created = created;
    }

    public CreatedObj getCreated() {
        return created;
    }
}
