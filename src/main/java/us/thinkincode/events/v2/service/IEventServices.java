package us.thinkincode.events.v2.service;

import us.thinkincode.events.v2.domain.Entity;
import us.thinkincode.events.v2.domain.Event;
import us.thinkincode.events.v2.domain.Task;

import java.util.List;

public interface IEventServices {

    Entity createEntity(Entity entity);

    Entity getEntity(String id);

    List<Entity> getEntities();

    Event createEvent(String entityId, Event event);

    Task createTask(String entityId, String eventId, Task task);

    List<Event> getEvents(String entityId);

    List<Task> getTasks(String entityId, String eventId);
}
