package us.thinkincode.events.v1.web;

import io.micronaut.security.token.jwt.validator.AuthenticationJWTClaimsSetAdapter;

import java.security.Principal;
import java.util.function.BiFunction;

public class WebFunctions {

    public static BiFunction<Principal, String, Boolean> notInAccount = (principal, accountId) ->
            !((AuthenticationJWTClaimsSetAdapter) principal).getAttributes().get("accountId").equals(accountId);

}
