package us.thinkincode.events.v3.service;

import us.thinkincode.events.v3.domain.SignupUser;
import us.thinkincode.events.v3.domain.User;

import javax.inject.Singleton;

import static us.thinkincode.events.v3.repository.InMemoryMappings.USERS;
import static us.thinkincode.events.v3.util.UtilityFunctions.generateUUID;

@Singleton
public class InMemoryAccountServiceImpl implements IAccountService {

    @Override
    public User saveUser(SignupUser signupUser) {
        var id = generateUUID.get();
        signupUser.setId(id);

        USERS.put(id, signupUser);

        return new User(signupUser);
    }

}
