package us.thinkincode.events.v4.service;

import us.thinkincode.events.v4.domain.SignupUser;
import us.thinkincode.events.v4.domain.User;

public interface IAccountService {

    User signupUser(SignupUser user);
}
