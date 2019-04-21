package us.thinkincode.events.v1.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.dto.PostEventRequest;
import us.thinkincode.events.v1.service.IEventServices;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

import static us.thinkincode.events.v1.web.WebFunctions.*;

@Controller("/api/v1/accounts/{accountId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class EventsController {

    static final MediaType APPLICATION_PDF = new MediaType("application/pdf");

    @Inject
    private IEventServices eventServices;

    @Get("/events")
    public HttpResponse<List<Event>> getEvents(@Value("accountId") String accountId) {
        return HttpResponse
                .<List<Event>>ok()
                .body(eventServices.getEvents(accountId));
    }

    @Get("/events.pdf")
    public StreamedFile getEventsPDF(@Value("accountId") String accountId) {
        return new StreamedFile(eventServices.getEventsPdf(accountId), APPLICATION_PDF);
    }

    @Post(uri = "/events", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Event> createEvent(
            @Value("accountId") String accountId, @Body PostEventRequest eventRequest, Principal principal) {

        if (notInAccount.apply(principal, accountId)) {
            return HttpResponse.unauthorized();
        }

        var eventResponse = eventServices.createEvent(accountId, eventRequest.toEvent(), principal.getName());

        return HttpResponse
                .<Event>ok()
                .body(eventResponse);
    }

    @Get("/events/{eventId}")
    public HttpResponse<Event> getEvent(@Value("accountId") String accountId, @Value("eventId") String eventId) {
        return HttpResponse
                .<Event>ok()
                .body(eventServices.getEvent(accountId, eventId));
    }

    @Post(uri = "/events/{eventId}/tasks", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Task> createTask(
            @Value("accountId") String accountId, @Value("eventId") String eventId, @Body Task task, Principal principal) {

        if (notInAccount.apply(principal, accountId)) {
            return HttpResponse.unauthorized();
        }

        Task taskResponse = eventServices.createTask(eventId, task, principal.getName());

        return HttpResponse
                .<Task>ok()
                .body(taskResponse);
    }

}
