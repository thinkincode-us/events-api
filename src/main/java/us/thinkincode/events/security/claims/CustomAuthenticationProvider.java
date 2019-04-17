package us.thinkincode.events.security.claims;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.providers.*;
import us.thinkincode.events.v1.domain.SignupUser;

import javax.inject.Singleton;
import java.util.List;

@Singleton
@Replaces(bean = DelegatingAuthenticationProvider.class)
public class CustomAuthenticationProvider extends DelegatingAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider(UserFetcher userFetcher, PasswordEncoder passwordEncoder, AuthoritiesFetcher authoritiesFetcher) {
        super(userFetcher, passwordEncoder, authoritiesFetcher);
    }

    protected AuthenticationResponse createSuccessfulAuthenticationResponse(AuthenticationRequest authenticationRequest,
            UserState user, List<String> authorities) {

        return new UserWithAccountIdDetails(user.getUsername(), authorities, ((SignupUser) user).getId());
    }
}
