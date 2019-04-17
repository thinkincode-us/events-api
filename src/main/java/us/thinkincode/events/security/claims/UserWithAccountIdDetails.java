package us.thinkincode.events.security.claims;

import io.micronaut.security.authentication.UserDetails;

import java.util.Collection;

public class UserWithAccountIdDetails extends UserDetails {

    private String accountId;

    public UserWithAccountIdDetails(String username, Collection<String> roles, String accountId) {
        super(username, roles);
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

}
