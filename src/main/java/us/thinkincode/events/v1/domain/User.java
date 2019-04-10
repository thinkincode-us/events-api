package us.thinkincode.events.v1.domain;

public class User  {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String phone;

    public User() {
    }

    public User(String id, String username, String firstName, String lastName, String phone) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;

    }

    public User(SignupUser signupUser) {
        this.id = signupUser.getId();
        this.username = signupUser.getUsername();
        this.firstName = signupUser.getFirstName();
        this.lastName = signupUser.getLastName();
        this.phone = signupUser.getPhone();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
