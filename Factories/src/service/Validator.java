package service;
import java.time.*;
import java.io.*;
import java.util.*;


public class Validator {  

    public static Boolean validateYesNo (String value) {
        Scanner userEntry = new Scanner(System.in);  

        while (true) {
            try {
                value = value.replaceAll("[^a-zA-Z]", "");
                if (value.toLowerCase().charAt(0) == 'y') {
                    return true;
                } else if (value.toLowerCase().charAt(0) == 'n') {
                    return false;
                } else {
                    System.out.print("Invalid entry. Must be y or n. Try again: ");
                    value = userEntry.nextLine();
                }
            } catch (Exception e) {
                System.out.print("Invalid entry. '" + e.getMessage() + "''. Try again: ");
                value = userEntry.nextLine();
            }
        }
    }

    public static int validateInteger (String numStr) {
        Scanner userEntry = new Scanner(System.in);  
        int num;

        while (true) {
            try {
                num = Integer.parseInt(numStr);
                if (num <= 0) {
                    throw new IllegalArgumentException("Value must be larger than 0");
                }
                return num;
            } catch (Exception e) {
                System.out.print("Invalid number. '" + e.getMessage() + "'. Try again: ");
                numStr = userEntry.nextLine();
            }   
        }
    }


}