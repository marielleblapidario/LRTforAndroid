package edu.mobidev.barrettokalingolapidario.newtestingproject;

/**
 * Created by vetkin123 on 10/27/2015.
 */
public class User {

    static final String TABLE_NAME = "user";
    static final String COLUMN_USER_ID = "UserId";
    static final String COLUMN_USER_FIRST_NAME = "userFirstName";
    static final String COLUMN_USER_LAST_NAME = "userLastName";
    static final String COLUMN_USERNAME = "username";
    static final String COLUMN_EMAIL = "email";
    static final String COLUMN_PASSWORD = "password";

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public User(){}

    public User(int userId, String firstName, String lastName, String username,String email, String password){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User( String firstName, String lastName, String username,String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
