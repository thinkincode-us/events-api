package us.thinkincode.events.v1.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.v1.dto.PostEventRequest;
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.service.IEventServices;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

@Controller("/api/v1/accounts/{accountId}/events")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class EventsController {

    @Inject
    private IEventServices eventServices;

    @Get
    public HttpResponse<List<Event>> getEvents(@Value("accountId") String accountId) {
        return HttpResponse
                .<List<Event>>ok()
                .body(eventServices.getEvents(accountId));
    }

    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Event> createEvent(
            @Value("accountId") String accountId, @Body PostEventRequest eventRequest, Principal principal) {

        var eventResponse = eventServices.createEvent(accountId, eventRequest.toEvent(), principal.getName());

        return HttpResponse
                .<Event>ok()
                .body(eventResponse);
    }

    @Get("/{eventId}")
    public HttpResponse<Event> getEvent(@Value("accountId") String accountId, @Value("eventId") String eventId) {
        return HttpResponse
                .<Event>ok()
                .body(eventServices.getEvent(accountId, eventId));
    }

    @Post(uri = "/{eventId}/tasks", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Task> createTask(@Value("eventId") String eventId, @Body Task task, Principal principal) {

        Task taskResponse = eventServices.createTask(eventId, task, principal.getName());

        return HttpResponse
                .<Task>ok()
                .body(taskResponse);
    }

}
