package us.thinkincode.events.v2.service;

import us.thinkincode.events.v2.domain.Entity;
import us.thinkincode.events.v2.domain.Event;
import us.thinkincode.events.v2.domain.Task;

import javax.inject.Singleton;
import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Singleton
public class EventServicesImpl implements IEventServices {

    private static Map<String, Entity> ENTITIES = new HashMap<>();

    static {
        var id1 = "123-1323-qwerty";
        ENTITIES.put(id1, new Entity(id1, "first name"));
        var id2 = "3527825362";
        ENTITIES.put(id2, new Entity(id2,"gomez"));
    }

    @Override
    public Entity createEntity(Entity entity) {

        var id = generateUUID.get();

        ENTITIES.put(id, entity);
        entity.setId(id);

        return entity;
    }

    @Override
    public Entity getEntity(String id) {
        return ENTITIES.getOrDefault(id, null);
    }

    @Override
    public List<Entity> getEntities() {
        return ENTITIES
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }

    @Override
    public Event createEvent(String entityId, Event event) {
        Entity entity = getPersistedEntity(entityId);

        entity.addEvent(event);
        event.setId(generateUUID.get());

        return event;
    }

    @Override
    public List<Event> getEvents(String entityId) {
        Entity entity = getPersistedEntity(entityId);

        return entity.getEvents();
    }

    @Override
    public Task createTask(String entityId, String eventId, Task task) {
        Entity entity = getPersistedEntity(entityId);

        Optional<Event> event = getPersistedEvent(entityId, eventId, entity);

        task.setId(generateUUID.get());
        event.get().addTasks(task);

        return task;
    }

    @Override
    public List<Task> getTasks(String entityId, String eventId) {
        Entity entity = getPersistedEntity(entityId);

        Optional<Event> event = getPersistedEvent(entityId, eventId, entity);

        return event.get().getTasks();
    }

    private Optional<Event> getPersistedEvent(String entityId, String eventId, Entity entity) {
        Optional<Event> event = entity.getEvents()
                .stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst();

        if (event.isEmpty()) throw new IllegalStateException("Entity " + entityId + " not found");
        return event;
    }

    private Entity getPersistedEntity(String entityId) {
        var entity = ENTITIES.get(entityId);

        if (entity == null) throw new IllegalStateException("Entity " + entityId + " not found");

        return entity;
    }

    private static final Supplier<String> generateUUID = () -> UUID.randomUUID().toString();
}