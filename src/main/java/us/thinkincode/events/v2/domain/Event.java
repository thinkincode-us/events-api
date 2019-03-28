package us.thinkincode.events.v2.domain;

import java.util.ArrayList;
import java.util.List;

public class Event {

    private String id;
    private String name;
    private List<Task> tasks;

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
}
