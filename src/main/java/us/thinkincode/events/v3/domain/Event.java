package us.thinkincode.events.v3.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private String id;
    private String name;
    private Entity entity;
    private List<Task> tasks;
    private CreatedObj created;

    public Event(String id, String name, Entity entity, String createdByUsername, LocalDateTime dtCreated) {
        this.id = id;
        this.name = name;
        this.entity = entity;
        this.created =  new CreatedObj(createdByUsername, dtCreated);
    }

    public void addTasks(Task task) {
        if (tasks == null) tasks = new ArrayList<>();

        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreatedObj getCreated() {
        return created;
    }

    public void setCreated(CreatedObj created) {
        this.created = created;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
