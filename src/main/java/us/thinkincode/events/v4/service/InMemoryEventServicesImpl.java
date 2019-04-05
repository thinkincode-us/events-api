package us.thinkincode.events.v4.service;

import us.thinkincode.events.v4.domain.CreatedObj;
import us.thinkincode.events.v4.domain.Event;
import us.thinkincode.events.v4.domain.Task;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static us.thinkincode.events.v4.util.UtilityFunctions.generateUUID;
import static us.thinkincode.events.v4.repository.InMemoryMappings.ACCOUNT_EVENTS;
import static us.thinkincode.events.v4.repository.InMemoryMappings.EVENTS_MASTER_CATALOG;

@Singleton
public class InMemoryEventServicesImpl implements IEventServices {

    @Override
    public List<Event> getEvents(String accountId) {
        return ACCOUNT_EVENTS
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(accountId))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public Event createEvent(String accountId, Event event, String username) {
        /* @TODO; implement
        var entityId = event.getEntity().getId();
        var entity = ACCOUNT_ENTITIES_CATALOG.get(accountId)
                .stream()
                .filter(entity1 -> entity1.getId().equals(entityId))
                .findFirst()
                .orElseThrow( () -> new IllegalStateException("Entity " + entityId + " not found"));

        ACCOUNT_EVENTS_CATALOG.get(accountId)
                .stream()
                .filter(eventCatalogItem -> eventCatalogItem.getId())

        var id = generateUUID.get();
        event.setId(id);
        event.setEntity(entity);
        event.setCreated(new CreatedObj(username, LocalDateTime.now()));

        return ACCOUNT_EVENTS.put(id, event);

        */

        return null;
    }

    @Override
    public Event getEvent(String eventId) {
        return ACCOUNT_EVENTS.get(eventId);
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
        /*
        Optional<Event> event = EVENTS_MASTER_CATALOG.values()
                .stream()
                .filter(e -> e.getId().equals(eventId))
                .findFirst();

        if (event.isEmpty()) throw new IllegalStateException("Event " + eventId + " not found");
        return event;
        */
        return null;
    }



}