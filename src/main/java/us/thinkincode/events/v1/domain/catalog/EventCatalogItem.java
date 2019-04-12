package us.thinkincode.events.v1.domain.catalog;

import us.thinkincode.events.v1.domain.CreatedObj;
import us.thinkincode.events.v1.domain.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventCatalogItem {

    private String id;
    private String name;
    private EntityCatalogItem entity;
    private List<Task> tasks;
    private CreatedObj created;

    public EventCatalogItem(String id, String name, EntityCatalogItem entity, String createdByUsername, LocalDateTime dtCreated, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.entity = entity;
        this.created =  new CreatedObj(createdByUsername, dtCreated);
        this.tasks = tasks;
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

    public EntityCatalogItem getEntity() {
        return entity;
    }

    public void setEntity(EntityCatalogItem entity) {
        this.entity = entity;
    }
}
