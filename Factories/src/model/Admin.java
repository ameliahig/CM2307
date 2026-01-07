package model;

public class Admin extends User{
    // admins can deactivate accounts and manage disputes

    public Admin(String username, String email, String password) {
        super(username, email, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void deactivateAccount(User user) {
        System.out.println("Admin deactivated account for " + user.username);
    }

    public void manageDispute() {
        System.out.println("Admin is managing a dispute");
    }

    @Override
    public String toString() {
        return "username: " + this.username + ", email: " + this.email + ", password: " + this.password;
    }


}