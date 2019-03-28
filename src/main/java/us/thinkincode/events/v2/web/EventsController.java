package us.thinkincode.events.v2.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.v2.domain.Entity;
import us.thinkincode.events.v2.domain.Event;
import us.thinkincode.events.v2.domain.Task;
import us.thinkincode.events.v2.service.IEventServices;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/api/v2/entities")
public class EventsController {

    @Inject
    private IEventServices eventServices;

    // curl -v localhost:8080/entities/
    @Get
    public HttpResponse getAllEntities(){
        return HttpResponse.ok().body(eventServices.getEntities());
    }

    // curl -v -H "Content-Type: application/json" --data '{"name": "aname"}' localhost:8080/entities
    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEntity(@Body Entity entity) {
        var responseEntity = eventServices.createEntity(entity);
        return HttpResponse
                .ok()
                .body(responseEntity);

    }

    // curl -v localhost:8080/events/entities/{id}
    @Get("/{id}")
    public HttpResponse getEntity(@Value("id") String id) {
        var entity = eventServices.getEntity(id);

        if (entity == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse
                .ok()
                .body(entity);
    }

    @Get("/{entityId}/events")
    public HttpResponse getEvents(@Value("entityId") String entityId) {
        var events = eventServices.getEvents(entityId);

        return HttpResponse
                .ok()
                .body(events);
    }

    @Post(uri = "/{entityId}/events", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEvent(@Value("entityId") String entityId, @Body Event event) {
        var eventResponse = eventServices.createEvent(entityId, event);

        return HttpResponse
                .ok()
                .body(eventResponse);
    }

    @Get("/{entityId}/events/{eventId}")
    public HttpResponse getTasks(@Value("entityId") String entityId, @Value("eventId") String eventId) {
        var tasks = eventServices.getTasks(entityId, eventId);

        return HttpResponse
                .ok()
                .body(tasks);
    }

    @Post(uri = "/{entityId}/events/{eventId}", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createTask(@Value("entityId") String entityId, @Value("eventId") String eventId, @Body Task task) {
        Task taskResponse = eventServices.createTask(entityId, eventId, task);

        return HttpResponse
                .ok()
                .body(taskResponse);
    }
}
