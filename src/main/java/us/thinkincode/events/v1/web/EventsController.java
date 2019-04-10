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
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.service.IEventServices;

import javax.inject.Inject;
import java.security.Principal;

@Controller("/api/v1/accounts/{accountId}/events")
@Secured(SecurityRule.IS_AUTHENTICATED)
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
    public HttpResponse getEvent(@Value("accountId") String accountId, @Value("eventId") String eventId) {
        return HttpResponse
                .ok()
                .body(eventServices.getEvent(accountId, eventId));
    }

    @Post(uri = "/events/{eventId}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createTask(@Value("eventId") String eventId, @Body Task task, Principal principal) {

        Task taskResponse = eventServices.createTask(eventId, task, principal.getName());

        return HttpResponse
                .ok()
                .body(taskResponse);
    }

}
