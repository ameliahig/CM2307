package model;

public class Homeowner extends User {
    // homeowners can create/edit/delete a listing, accept/reject booking requests
    public Homeowner(String username, String email, String password) {
        super(username, email, password);
    }

    public void menu() {
        System.out.println();
    }
}
