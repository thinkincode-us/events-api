package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.Entity;

import java.util.List;

public interface IEventServices {

    Entity  createEntity(Entity entity);

    Entity getEntity(String id);

    List<Entity> getAll();
}
