package us.thinkincode.events.v3.security;

import io.micronaut.security.authentication.providers.AuthoritiesFetcher;
import io.micronaut.security.authentication.providers.PasswordEncoder;
import io.micronaut.security.authentication.providers.UserFetcher;
import io.micronaut.security.authentication.providers.UserState;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import us.thinkincode.events.v3.domain.SignupUser;

import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

import static java.lang.System.out;
import static us.thinkincode.events.v3.repository.InMemoryMappings.USERS;

@Singleton
class AuthenticationService implements UserFetcher, AuthoritiesFetcher, PasswordEncoder {

    private org.springframework.security.crypto.password.PasswordEncoder encoderDelegate = new BCryptPasswordEncoder();

    @Override
    public Publisher<UserState> findByUsername(String username) {

        Optional<SignupUser> userOpt =  USERS.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();

        return userOpt.isPresent() ? Flowable.just(userOpt.get()) : Flowable.empty();
    }

    @Override
    public Publisher<List<String>> findAuthoritiesByUsername(String username) {
        return Flowable.just(List.of("Authority1"));
    }

    public String encode(String rawPassword) {
        return encoderDelegate.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoderDelegate.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        AuthenticationService encoderService = new AuthenticationService();
        out.println("Encoded password: " + encoderService.encode(args[0]));
    }
}