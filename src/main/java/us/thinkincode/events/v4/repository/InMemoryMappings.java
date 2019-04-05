package us.thinkincode.events.v4.repository;

import us.thinkincode.events.v4.domain.Event;
import us.thinkincode.events.v4.domain.SignupUser;
import us.thinkincode.events.v4.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v4.domain.catalog.EventCatalogItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMappings {

    public static Map<String, EntityCatalogItem> ENTITIES_MASTER_CATALOG = new HashMap<>();
    public static Map<String, EventCatalogItem> EVENTS_MASTER_CATALOG = new HashMap<>();

    public static Map<String, List<EntityCatalogItem>> ACCOUNT_ENTITIES_CATALOG = new HashMap<>();
    public static Map<String, List<EventCatalogItem>> ACCOUNT_EVENTS_CATALOG = new HashMap<>();

    public static Map<String, Event> ACCOUNT_EVENTS = new HashMap<>();

    public static Map<String, SignupUser> USERS = new HashMap<>();
}
