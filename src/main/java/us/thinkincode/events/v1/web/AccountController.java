package us.thinkincode.events.v1.web;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import us.thinkincode.events.dto.RegisterRequest;
import us.thinkincode.events.v1.domain.User;
import us.thinkincode.events.v1.service.IAccountService;

import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

@Controller("/api/v1/account")
public class AccountController {

    @Inject
    private IAccountService accountService;

    @Post("/register")
    public User signupUser(@Body RegisterRequest registerRequest) {
        return accountService.signupUser(registerRequest.toSignupUser());
    }

    @Get("/users")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public  List<User> getUsers(Principal principal) {
        return accountService.getUsers(principal.getName());
    }

}

