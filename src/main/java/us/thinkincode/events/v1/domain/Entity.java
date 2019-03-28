package us.thinkincode.events.v1.domain;

public class Entity {

    public Entity() {

    }

    public Entity(String id, String name) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private String id;

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
