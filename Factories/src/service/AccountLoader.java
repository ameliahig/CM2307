package service;
import factory.UserFactory;
//import factory.AdminFactory;
//import model.Admin;
import model.User;

import java.util.*;
import java.io.*;

public class AccountLoader {

    public static void loadAccounts(String filename, UserFactory studentFactory, UserFactory homeownerFactory, UserFactory adminFactory, List<User> users) {

        try (Scanner fileReader = new Scanner(new File(filename))) {
            while (fileReader.hasNextLine()) {
                String[] parts = fileReader.nextLine().split(" ");
                String username = parts[0];
                String password = parts[1];
                String role = parts[2];
                String email = parts[3];

                if (role.equals("student")) {
                    users.add(studentFactory.registerUser(username, email, password));
                } else if (role.equals("homeowner")) {
                    users.add(homeownerFactory.registerUser(username, email, password));
                } else if (role.equals("admin")) {
                    users.add(adminFactory.registerUser(username, email, password));
                } else {
                    System.out.println("Unknown role: " + role);;
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }
    }
    
}

