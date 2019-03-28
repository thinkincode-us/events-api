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
import us.thinkincode.events.v1.domain.Entity;
import us.thinkincode.events.v1.service.IEventServices;

import javax.inject.Inject;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/events")
public class EventsController {

    @Inject
    private IEventServices eventServices;

    // curl -v -H "Content-Type: application/json" --data '{"name": "it works!"}' localhost:8080/events
    @Post(consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEntity(@Body Entity entity) {
        Entity responseEntity = eventServices.createEntity(entity);
        return HttpResponse
                .ok()
                .body(responseEntity);

    }

    // curl -v localhost:8080/events/events/123
    @Get("/{id}")
    public HttpResponse getEntity(@Value("id") String id) {
        Entity entity = eventServices.getEntity(id);

        if (entity == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse.ok().body(entity);
    }

    // curl -v localhost:8080/events/events
    @Get
    public HttpResponse getAllEntities(){
        return HttpResponse.ok().body(eventServices.getAll());
    }
}
