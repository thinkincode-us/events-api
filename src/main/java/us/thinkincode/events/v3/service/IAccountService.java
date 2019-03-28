package us.thinkincode.events.v3.service;

import us.thinkincode.events.v3.domain.SignupUser;
import us.thinkincode.events.v3.domain.User;

public interface IAccountService {

    User saveUser(SignupUser user);
}
