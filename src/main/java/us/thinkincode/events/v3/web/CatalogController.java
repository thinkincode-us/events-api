package us.thinkincode.events.v3.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.v3.domain.Entity;
import us.thinkincode.events.v3.domain.Event;
import us.thinkincode.events.v3.domain.Task;
import us.thinkincode.events.v3.service.IEventServices;

import javax.inject.Inject;
import java.security.Principal;

@Controller("/api/v1/catalog")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CatalogController {

    @Inject
    private IEventServices eventServices;

    @Get("/entities")
    public HttpResponse getAllEntities() {
        return HttpResponse
                .ok()
                .body(eventServices.getEntities());
    }

    @Post(uri = "/entities", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEntity(@Body Entity entity, Principal principal) {
        var responseEntity = eventServices.createEntity(entity, principal.getName());
        return HttpResponse
                .ok()
                .body(responseEntity);
    }

    @Get("/entities/{id}")
    public HttpResponse getEntity(@Value("id") String id) {
        var entity = eventServices.getEntity(id);

        if (entity == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse
                .ok()
                .body(entity);
    }

    @Get("/events")
    public HttpResponse getEvents(@QueryValue("entityId") String entityId) {
        var events = eventServices.getEvents(entityId);

        return HttpResponse
                .ok()
                .body(events);
    }

    @Post(uri = "/events", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEvent(@Body Event event, Principal principal) {
        var eventResponse = eventServices.createEvent(event, principal.getName());

        return HttpResponse
                .ok()
                .body(eventResponse);
    }

    @Get("/events/{eventId}")
    public HttpResponse getTasks(@Value("eventId") String eventId) {
        var tasks = eventServices.getTasks(eventId);

        return HttpResponse
                .ok()
                .body(tasks);
    }

    @Post(uri = "/events/{eventId}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createTask(@Value("eventId") String eventId, @Body Task task, Principal principal) {

        Task taskResponse = eventServices.createTask(eventId, task, principal.getName());

        return HttpResponse
                .ok()
                .body(taskResponse);
    }
}
