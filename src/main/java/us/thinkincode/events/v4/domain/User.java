package us.thinkincode.events.v4.domain;

public class User  {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;

    public User(SignupUser signupUser) {
        this.id = signupUser.getId();
        this.username = signupUser.getUsername();
        this.firstName = signupUser.getFirstName();
        this.lastName = signupUser.getLastName();
        this.phone = signupUser.getPhone();

    }
}
