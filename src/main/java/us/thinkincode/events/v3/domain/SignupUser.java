package us.thinkincode.events.v3.domain;

import io.micronaut.security.authentication.providers.UserState;

public class SignupUser implements UserState {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    public SignupUser(String id, String username, String password, String firstName, String lastName, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountExpired() {
        return false;
    }

    @Override
    public boolean isAccountLocked() {
        return false;
    }

    @Override
    public boolean isPasswordExpired() {
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(String id) {
        this.id = id;
    }
}
