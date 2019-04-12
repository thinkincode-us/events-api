package us.thinkincode.events.dto;

import us.thinkincode.events.v1.domain.Event;

public class EventRequest {

    private String eventCatalogId;
    private String name;
    private String entityId;
    private String entityName;

    public Event toEvent() {
        return new Event(eventCatalogId, name, entityId, entityName);
    }

    public void setEventCatalogId(String eventCatalogId) {
        this.eventCatalogId = eventCatalogId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEventCatalogId() {
        return eventCatalogId;
    }

    public String getName() {
        return name;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getEntityName() {
        return entityName;
    }
}
