package us.thinkincode.events.v4.web;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import us.thinkincode.events.v4.domain.SignupUser;
import us.thinkincode.events.v4.domain.User;
import us.thinkincode.events.v4.service.IAccountService;

@Controller("/api/v1/account")
public class AccountController {

    private IAccountService accountService;

    @Post("/register")
    public User signupUser(@Body SignupUser signupUser) {
        return accountService.signupUser(signupUser);
    }

}

