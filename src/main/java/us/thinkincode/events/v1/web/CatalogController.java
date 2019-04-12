package us.thinkincode.events.v1.web;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;
import us.thinkincode.events.v1.service.ICatalogServices;

import javax.inject.Inject;
import java.security.Principal;

@Controller("/api/v1/{accountId}/catalog")
@Secured(SecurityRule.IS_AUTHENTICATED)
public class CatalogController {

    @Inject
    private ICatalogServices catalogServices;

    @Get("/entities")
    public HttpResponse getEntities(@Value("accountId") String accountId) {
        return HttpResponse
                .ok()
                .body(catalogServices.getCatalogEntities(accountId));
    }

    @Post(uri = "/entities", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEntity(@Value("accountId") String accountId, @Body EntityCatalogItem entity, Principal principal) {

        var responseEntity = catalogServices.createCatalogEntity(accountId, entity, principal.getName());
        return HttpResponse
                .ok()
                .body(responseEntity);
    }

    @Get("/entities/{id}")
    public HttpResponse getEntity(@Value("accountId") String accountId, @Value("id") String id) {
        var entity = catalogServices.getCatalogEntity(accountId, id);

        if (entity == null) {
            return HttpResponse.notFound();
        }

        return HttpResponse
                .ok()
                .body(entity);
    }

    @Get("/events")
    public HttpResponse getEvents(@Value("accountId") String accountId, @QueryValue("entityId") String entityId) {
        var events = catalogServices.getEventsCatalog(accountId, entityId);

        return HttpResponse
                .ok()
                .body(events);
    }

    @Post(uri = "/events", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse createEvent(@Body EventCatalogItem event, Principal principal) {
        var eventResponse = catalogServices.createCatalogEvent(event, principal.getName());

        return HttpResponse
                .ok()
                .body(eventResponse);
    }

}