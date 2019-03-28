package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.Entity;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Singleton
public class EventServicesImpl implements IEventServices {

    static Map<String, Entity> entities = new HashMap<>();

    static {
        String id1 = "123-1323-qwerty";
        entities.put(id1, new Entity(id1, "first name"));
        String id2 = "3527825362";
        entities.put(id2, new Entity(id2,"gomez"));
    }

    @Override
    public Entity createEntity(Entity entity) {

        String id = UUID.randomUUID().toString();
        entities.put(id, entity);
        entity.setId(id);

        return entity;
    }

    @Override
    public Entity getEntity(String id) {
        return entities.getOrDefault(id, null);
    }

    @Override
    public List<Entity> getAll() {
        return entities
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(toList());
    }
}
