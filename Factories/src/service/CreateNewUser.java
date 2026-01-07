package service;
import model.User;
import model.Admin;
import factory.UserFactory;
import factory.AdminFactory;
import java.util.*;
import java.io.*;

public class CreateNewUser {
    private Scanner userEntry = new Scanner(System.in);
    private List<User> users; // list of existing users
    private UserFactory studentFactory;
    private UserFactory homeownerFactory;

    public CreateNewUser(List<User> users, UserFactory studentFactory, UserFactory homeownerFactory) {
        this.users = users;
        this.studentFactory = studentFactory;
        this.homeownerFactory = homeownerFactory;
    }

    // main method to create new user
    public User createUser() {
        String roleType = chooseRole();
        String username = chooseUsername();
        String email = chooseEmail();
        String password = choosePassword();

        User newUser;
        if (roleType.equals("student")) {
            newUser = studentFactory.registerUser(username, email, password);
        } else {
            newUser = homeownerFactory.registerUser(username, email, password);
        }

        // append the new user to accounts.txt
        saveToFile(username, password, roleType, email);
        
        return newUser; 
    }

    private String chooseRole() {
        String roleType;
        while (true) {
            try { 
                System.out.print("Would you like to sign up as a Student (S) or Homeowner (H): ");
                roleType = userEntry.nextLine().strip();
                
                if (roleType.toLowerCase().charAt(0) == 's') {
                    return "student";
                
                } else if (roleType.toLowerCase().charAt(0) == 'h') {
                    return "homeowner";
                
                } else { 
                    System.out.println("Invalid role. Please try again. ");
                }
                
            } catch (Exception e) {
                System.out.print("Role selection failed. Try again. ");
            }   
        }  
    }

    private String chooseUsername() {
        String username;
        while (true) {
            try { 
                System.out.print("Enter a username: ");
                username = userEntry.nextLine().strip().toLowerCase();
                
                if (username.contains("?") || username.contains(".") || username.contains("-")) {
                    System.out.print("Your username: " + username + ", included protected characters '(?.-). Please try again. ");
                    // go out the loop and restart
                
                } else if (username.length() <=3) {
                    System.out.print("Your username: " + username + ", is too short. Please enter more than 3 characters. ");
                    // go out the loop and restart
                
                } else if (username.length() >= 20) {
                    System.out.print("Your username is too long. Please enter between 4 and 19 characters. ");
                    // go out the loop and restart
                
                } else if (usernameUnique(username) == false) {
                    System.out.println("That username is already taken. Please enter something different. ");
                    
                } else {
                    return username;
                }
            } catch (Exception e) {
                System.out.print("Username selection failed. Try again. ");
            }   
        }  
    }

    private boolean usernameUnique(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return false;
            }
        }
        return true;
    }

    private String chooseEmail() {
        String email;
        while (true) {
            try { 
                System.out.print("Enter your email: ");
                email = userEntry.nextLine().strip();
                
                if (emailUnique(email) == false) {
                    System.out.println("That email already exists. Please enter something different. ");
                    
                } else {
                    return email;
                }
            } catch (Exception e) {
                System.out.print("Email entry failed. Try again. ");
            }   
        }
    }

    private boolean emailUnique(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    private String choosePassword() {
        System.out.print("Choose a password: ");
        return userEntry.nextLine().strip();           
    }

    private void saveToFile(String username, String password, String roleType, String email) {
        String textLine = username + " " + password + " " + roleType + " " + email;
        try {
            FileWriter accountsFile = new FileWriter("../resources/accounts.txt", true);
            accountsFile.write(textLine + "\n");
            accountsFile.close();
        } catch (Exception e) {
            System.out.println("Error occurred when writing to file: " + e.getMessage());
        }      
    }
}