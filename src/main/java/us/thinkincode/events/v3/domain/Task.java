package us.thinkincode.events.v3.domain;

public class Task {

    private String id;
    private String name;
    private CreatedObj created;

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
}
