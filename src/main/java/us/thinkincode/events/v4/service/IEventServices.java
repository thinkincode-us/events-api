package us.thinkincode.events.v4.service;

import us.thinkincode.events.dto.CreateEventRequest;
import us.thinkincode.events.v4.domain.Event;
import us.thinkincode.events.v4.domain.Task;

import java.util.List;

public interface IEventServices {

    Event createEvent(String accountId, Event requestObj, String username);

    List<Event> getEvents(String accountId);

    Event getEvent(String eventId);

    Task createTask(String eventId, Task task, String username);

    List<Task> getTasks(String eventId);


}
