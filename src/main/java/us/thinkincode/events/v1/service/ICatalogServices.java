package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;

import java.util.List;

public interface ICatalogServices {

    EntityCatalogItem createCatalogEntity(String accountId, EntityCatalogItem entity, String username);

    EntityCatalogItem getCatalogEntity(String accountId, String id);

    List<EntityCatalogItem> getCatalogEntities(String accountId);

    EventCatalogItem createCatalogEvent(EventCatalogItem event, String username);

    List<EventCatalogItem> getEventsCatalog(String accountId, String entityId);

}
