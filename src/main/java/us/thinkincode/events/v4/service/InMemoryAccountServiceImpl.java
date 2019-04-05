package us.thinkincode.events.v4.service;

import us.thinkincode.events.v4.domain.SignupUser;
import us.thinkincode.events.v4.domain.User;
import us.thinkincode.events.v4.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v4.domain.catalog.EventCatalogItem;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static us.thinkincode.events.v4.repository.InMemoryMappings.*;
import static us.thinkincode.events.v4.util.UtilityFunctions.generateUUID;

@Singleton
public class InMemoryAccountServiceImpl implements IAccountService {

    @Override
    public User signupUser(SignupUser signupUser) {
        var id = generateUUID.get();
        signupUser.setId(id);

        USERS.put(id, signupUser);

        List<EntityCatalogItem> clonedEntities = ENTITIES_MASTER_CATALOG.values()
                .stream()
                .map(value -> new EntityCatalogItem(
                        value.getId(), //@TODO: avoid collisions
                        value.getName(),
                        value.getCreated().getByUsername(),
                        value.getCreated().getDate()))
                .collect(Collectors.toList());

        List<EventCatalogItem> clonedEvents = EVENTS_MASTER_CATALOG.values()
                .stream()
                .map(value -> new EventCatalogItem(
                        value.getId(),
                        value.getName(),
                        value.getEntity(),
                        value.getCreated().getByUsername(),
                        value.getCreated().getDate()
                )).collect(Collectors.toList());

        ACCOUNT_ENTITIES_CATALOG.put(id, clonedEntities);
        ACCOUNT_EVENTS_CATALOG.put(id, clonedEvents);

        return new User(signupUser);
    }

}
