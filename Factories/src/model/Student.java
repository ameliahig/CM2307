package model;

public class Student extends User {
    // students can search for a room, request a booking
    public Student(String username, String email, String password) {
        super(username, email, password);
    }

}