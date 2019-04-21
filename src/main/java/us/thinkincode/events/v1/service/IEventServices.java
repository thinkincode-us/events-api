package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;

import java.io.InputStream;
import java.util.List;

public interface IEventServices {

    Event createEvent(String accountId, Event requestObj, String username);

    List<Event> getEvents(String accountId);

    InputStream getEventsPdf(String accountId);

    Event getEvent(String accountId, String eventId);

    Task createTask(String eventId, Task task, String username);

    List<Task> getTasks(String eventId);


}
