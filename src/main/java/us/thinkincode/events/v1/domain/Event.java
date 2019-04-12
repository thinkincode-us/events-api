package us.thinkincode.events.v1.domain;

import us.thinkincode.events.v1.util.UtilityFunctions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private String id;
    private String parentId;
    private String name;
    private String entityId;
    private String entityName;
    private List<Task> tasks;
    private CreatedObj created;

    public Event(String id, String name, String entityId, String entityName, String createdByUsername, LocalDateTime dtCreated, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.entityId = entityId;
        this.entityName = entityName;
        this.created =  new CreatedObj(createdByUsername, dtCreated);
        this.tasks = tasks;
    }

    public Event(String parentId, String name, String entityId, String entityName) {
        this.id = UtilityFunctions.generateUUID.get();
        this.parentId = parentId;
        this.name = name;
        this.entityId = entityId;
        this.entityName = entityName;
    }

    public void addTasks(Task task) {
        if (tasks == null) tasks = new ArrayList<>();

        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getParentId() {
        return parentId;
    }
}
