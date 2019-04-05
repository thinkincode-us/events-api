package us.thinkincode.events.v4.service;

import us.thinkincode.events.v4.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v4.domain.catalog.EventCatalogItem;

import javax.inject.Singleton;
import java.util.List;

import static us.thinkincode.events.v4.repository.InMemoryMappings.ACCOUNT_ENTITIES_CATALOG;

@Singleton
public class InMemoryCatalogServicesImpl implements ICatalogServices {

    @Override
    public EntityCatalogItem createCatalogEntity(EntityCatalogItem entity, String username) {
        return null;
    }

    @Override
    public EntityCatalogItem getCatalogEntity(String id) {
        return null;
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
        return null;
    }
}