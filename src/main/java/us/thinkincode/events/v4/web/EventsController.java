package us.thinkincode.events.v4.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import us.thinkincode.events.dto.CreateEventRequest;
import us.thinkincode.events.v4.domain.Event;
import us.thinkincode.events.v4.domain.Task;
import us.thinkincode.events.v4.service.IEventServices;

import javax.inject.Inject;
import java.security.Principal;

@Controller("/api/v1/{accountId}/events")
public class EventsController {

    @Inject
    private IEventServices eventServices;

    @Get
    public HttpResponse getEvents(@Value("accountId") String accountId) {
        return HttpResponse
                .ok()
                .body(eventServices.getEvents(accountId));
    }

    @Post(uri = "/events", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEvent(@Value("accountId") String accountId, @Body Event event, Principal principal) {
        var eventResponse = eventServices.createEvent(accountId, event, principal.getName());

        return HttpResponse
                .ok()
                .body(eventResponse);
    }

    @Get("/events/{eventId}")
    public HttpResponse getEvent(@Value("eventId") String eventId) {
        return HttpResponse
                .ok()
                .body(eventServices.getEvent(eventId));
    }

    @Post(uri = "/events/{eventId}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createTask(@Value("eventId") String eventId, @Body Task task, Principal principal) {

        Task taskResponse = eventServices.createTask(eventId, task, principal.getName());

        return HttpResponse
                .ok()
                .body(taskResponse);
    }

}
