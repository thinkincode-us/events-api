package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.SignupUser;
import us.thinkincode.events.v1.domain.User;
import us.thinkincode.events.v1.domain.catalog.EntityCatalogItem;
import us.thinkincode.events.v1.domain.catalog.EventCatalogItem;
import io.micronaut.security.authentication.providers.PasswordEncoder;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

import static us.thinkincode.events.v1.repository.InMemoryMappings.*;
import static us.thinkincode.events.v1.util.UtilityFunctions.generateUUID;

@Singleton
public class InMemoryAccountServiceImpl implements IAccountService {

    @Inject
    PasswordEncoder  passwordEncrypter;

    @Override
    public User signupUser(SignupUser signupUser) {
        var id = generateUUID.get();
        signupUser.setId(id);
        String encodedPassword = passwordEncrypter.encode(signupUser.getPassword());
        signupUser.setPassword(encodedPassword);

        USERS.put(id, signupUser);

        return new User(signupUser);
    }

    @Override
    public List<User> getUsers(String user) {
        var accountId = USERS.values()
                .stream()
                .filter(usr -> usr.getUsername().equals(user))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("User not found"))
                .getId();

        return List.of(USERS.get(accountId).toUser());
    }


    public static void onboard(String accountId) {
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
                        value.getCreated().getDate(),
                        value.getTasks()
                )).collect(Collectors.toList());

        ACCOUNT_ENTITIES_CATALOG.put(accountId, clonedEntities);
        ACCOUNT_EVENTS_CATALOG.put(accountId, clonedEvents);
    }

}
