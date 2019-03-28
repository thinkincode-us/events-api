package us.thinkincode.events.v3.service;

import us.thinkincode.events.v3.domain.Entity;
import us.thinkincode.events.v3.domain.Event;
import us.thinkincode.events.v3.domain.Task;

import java.util.List;

public interface IEventServices {

    Entity createEntity(Entity entity, String username);

    Entity getEntity(String id);

    List<Entity> getEntities();

    Event createEvent(Event event, String username);

    List<Event> getEvents(String entityId);

    Task createTask(String eventId, Task task, String username);

    List<Task> getTasks(String eventId);
}
