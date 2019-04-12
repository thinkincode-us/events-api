package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static us.thinkincode.events.v1.repository.InMemoryMappings.ACCOUNT_ENTITIES_CATALOG;
import static us.thinkincode.events.v1.repository.InMemoryMappings.ACCOUNT_EVENTS_CATALOG;

@Singleton
public class InMemoryCatalogServicesImpl implements ICatalogServices {

    @Override
    public EntityCatalogItem createCatalogEntity(String accountId, EntityCatalogItem entity, String username) {
        // @TODO: implement
        return null;
    }

    @Override
    public EntityCatalogItem getCatalogEntity(String accountId, String id) {
        return ACCOUNT_ENTITIES_CATALOG.get(accountId)
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Entity not found"));
    }

    @Override
    public List<EntityCatalogItem> getCatalogEntities(String accountId) {
            return ACCOUNT_ENTITIES_CATALOG.get(accountId);
    }

    @Override
    public EventCatalogItem createCatalogEvent(EventCatalogItem event, String username) {
        return null;
    }

    @Override
    public List<EventCatalogItem> getEventsCatalog(String accountId, String entityId) {
        return ACCOUNT_EVENTS_CATALOG.get(accountId)
                .stream()
                .filter(eventCatalogItem -> eventCatalogItem.getEntity().getId().equals(entityId))
                .collect(Collectors.toList());
    }
}