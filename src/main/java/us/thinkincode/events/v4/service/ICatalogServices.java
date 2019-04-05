package us.thinkincode.events.v4.service;

import us.thinkincode.events.v4.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v4.domain.catalog.EventCatalogItem;

import java.util.List;

public interface ICatalogServices {

    EntityCatalogItem createCatalogEntity(EntityCatalogItem entity, String username);

    EntityCatalogItem getCatalogEntity(String id);

    List<EntityCatalogItem> getCatalogEntities(String accountId);

    EventCatalogItem createCatalogEvent(EventCatalogItem event, String username);

    List<EventCatalogItem> getEventsCatalog(String accountId, String entityId);

}
