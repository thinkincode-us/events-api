package us.thinkincode.events.v1.domain.catalog;

import us.thinkincode.events.v1.domain.CreatedObj;

import java.time.LocalDateTime;

public class EntityCatalogItem {

    private String name;
    private String id;
    private CreatedObj created;

    public EntityCatalogItem() {

    }

    public EntityCatalogItem(String id, String name, String createdByUsername, LocalDateTime dtCreated) {
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
