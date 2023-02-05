
public class User {
    private String email;
    private String password;
    private String dateOfBirth;


    public String getEmail() {
        return email;
    }

    public User(String email, String password, String dateOfBirth) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean register() {
        // Code to register the user in the application
        return true;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean login(String email, String password) {
        // Code to check if the provided email and password match the user
        return this.email.equals(email) && this.password.equals(password);
    }
}