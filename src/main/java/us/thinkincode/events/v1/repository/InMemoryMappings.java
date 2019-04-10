package us.thinkincode.events.v1.repository;

import us.thinkincode.events.v1.domain.Event;
import us.thinkincode.events.v1.domain.SignupUser;
import us.thinkincode.events.v1.domain.Task;
import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryMappings {

    public static Map<String, EntityCatalogItem> ENTITIES_MASTER_CATALOG = new HashMap<>();
    public static Map<String, EventCatalogItem> EVENTS_MASTER_CATALOG = new HashMap<>();

    public static Map<String, List<EntityCatalogItem>> ACCOUNT_ENTITIES_CATALOG = new HashMap<>();
    public static Map<String, List<EventCatalogItem>> ACCOUNT_EVENTS_CATALOG = new HashMap<>();

    public static Map<String, List<Event>> ACCOUNT_EVENTS = new HashMap<>();
    public static Map<String, List<Task>> ACCOUNT_TASKS = new HashMap<>();

    public static Map<String, SignupUser> USERS = new HashMap<>();
}
