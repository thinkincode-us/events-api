package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.domain.User;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static us.thinkincode.events.v1.repository.InMemoryMappings.ACCOUNT_EVENTS;
import static us.thinkincode.events.v1.repository.InMemoryMappings.ACCOUNT_EVENTS_CATALOG;
import static us.thinkincode.events.v1.util.UtilityFunctions.generateUUID;

@Singleton
public class InMemoryEventServicesImpl implements IEventServices {

    @Inject IAccountService accountService;

    @Override
    public List<Event> getEvents(String accountId) {
        return ACCOUNT_EVENTS
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(accountId))
                .findFirst()
                .orElseGet(() -> Map.entry("", List.of()))
                .getValue();
    }

    @Override
    public Event createEvent(String accountId, Event event, String username) {
        User user = accountService.getUsers(username).get(0);

        EventCatalogItem eventCatalogItem = ACCOUNT_EVENTS_CATALOG.get(user.getId())
                .stream()
                .filter(catalogItem -> catalogItem.getId().equals(event.getParentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Event not found"));

        event.setTasks(eventCatalogItem.getTasks());

        ACCOUNT_EVENTS.get(user.getId())
                .add(event);

        return event;

    }

    @Override
    public Event getEvent(String accountId, String eventId) {
        return ACCOUNT_EVENTS.get(accountId)
                .stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Event not found"));
    }

    @Override
    public Task createTask(String eventId, Task task, String username) {

        Optional<Event> event = getPersistedEvent(eventId);

        task.setId(generateUUID.get());
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