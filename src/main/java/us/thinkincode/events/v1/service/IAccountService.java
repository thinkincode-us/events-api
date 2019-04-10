package us.thinkincode.events.v1.service;

import us.thinkincode.events.v1.domain.SignupUser;
import us.thinkincode.events.v1.domain.User;

import java.util.List;

public interface IAccountService {

    User signupUser(SignupUser user);

    List<User> getUsers(String user);
}
