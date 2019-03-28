package us.thinkincode.events.v3.service;

import us.thinkincode.events.v3.domain.CreatedObj;
import us.thinkincode.events.v3.domain.Entity;
import us.thinkincode.events.v3.domain.Event;
import us.thinkincode.events.v3.domain.Task;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static us.thinkincode.events.v3.repository.InMemoryMappings.ENTITIES;
import static us.thinkincode.events.v3.repository.InMemoryMappings.EVENTS;
import static us.thinkincode.events.v3.util.UtilityFunctions.generateUUID;

@Singleton
public class InMemoryEventServicesImpl implements IEventServices {

    @Override
    public Entity createEntity(Entity entity, String username) {

        var id = generateUUID.get();

        entity.setId(id);
        entity.setCreated(new CreatedObj(username, LocalDateTime.now()));

        return ENTITIES.put(id, entity);
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
    public Event createEvent(Event event, String username) {
        var entityId = event.getEntity().getId();
        var entity = ENTITIES.get(entityId);
        if (entity == null) throw new IllegalStateException("Entity " + entityId + " not found");

        var id = generateUUID.get();
        event.setId(id);
        event.setEntity(entity);
        event.setCreated(new CreatedObj(username, LocalDateTime.now()));

        return EVENTS.put(id, event);
    }

    @Override
    public List<Event> getEvents(String entityId) {

        return EVENTS.values()
                .stream()
                .filter(event -> event.getEntity().getId().equals(entityId))
                .collect(Collectors.toList());
    }

    @Override
    public Task createTask(String eventId, Task task, String username) {

        Optional<Event> event = getPersistedEvent(eventId);

        task.setId(generateUUID.get());
        task.setCreated(new CreatedObj(username, LocalDateTime.now()));
        event.get().addTasks(task);

        return task;
    }

    @Override
    public List<Task> getTasks(String eventId) {
        Optional<Event> event = getPersistedEvent(eventId);

        return event.get().getTasks();
    }

    private Optional<Event> getPersistedEvent(String eventId) {
        Optional<Event> event = EVENTS.values()
                .stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst();

        if (event.isEmpty()) throw new IllegalStateException("Event " + eventId + " not found");
        return event;
    }

}