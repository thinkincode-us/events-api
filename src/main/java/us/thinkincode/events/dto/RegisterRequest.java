package us.thinkincode.events.dto;

import us.thinkincode.events.v1.domain.SignupUser;

public class RegisterRequest {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    public SignupUser toSignupUser() {
        return new SignupUser(null, username, password, firstName, lastName, phone);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
