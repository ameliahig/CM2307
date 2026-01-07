package service;
import model.RoomListing;
import service.Validator;
import java.time.*;
import java.util.*;

public class BookingService {

    public boolean bookingOverlap(RoomListing listing, LocalDate proposedStart, int weekDuration) {
        LocalDate proposedEnd = proposedStart.plusWeeks(weekDuration);

        // loops through all existing bookings for a specific listing
        for (Map.Entry<LocalDate, Integer> entry : listing.getUnavailDates().entrySet()) {
            LocalDate existingStart = entry.getKey();
            LocalDate existingEnd = existingStart.plusWeeks(entry.getValue());

            if (proposedStart.isBefore(existingEnd) && existingStart.isBefore(proposedEnd)) {
                return true;
            }
        }
        return false;
    }

    // userDate should be in the format YYYY-MM-DD
    public LocalDate validateDate(String userDate) {
        String error = "";
        int year = 0; int month = 0; int day = 0;
        LocalDate startDate;
        Scanner userEntry = new Scanner(System.in);  

        while (true) {

            try {
            
                String[] parts = userDate.strip().split("-");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Incorrect date format. Ensure you have entered 3 numbers separated by '-'.");
                }

                year = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
            
                startDate = LocalDate.of(year, month, day);
                if (startDate.isBefore(LocalDate.now())) {
                    throw new IllegalArgumentException("Date entered must be future (today or later).");
                }
            
                return startDate;
                
                } catch (NumberFormatException e) {
                    System.out.print("Dates must be in number format. Try again: ");
                    userDate = userEntry.nextLine();

                } catch (Exception e) {
                    System.out.print(e.getMessage() + " Try again: ");
                    userDate = userEntry.nextLine();
                }
        }
    }

    // this method is currently not being used #broken
    // public Map<LocalDate, Integer> additionalBookings() {
    //     Scanner userEntry = new Scanner(System.in);  
    //     String startDateStr; LocalDate startDate; String weekLengthStr; int weekLength;

    //     System.out.print("Do you currently have any upcoming bookings for this property? Y/N: ");
    //     String response = userEntry.nextLine();
    //     boolean upcoming = validateYesNo(response);
        
    //     while (upcoming == true) {
    //         try {
    //             System.out.print("What date does this booking start? YYYY-MM-DD: ");
    //             startDateStr = userEntry.nextLine();
    //             startDate = validateDate(startDateStr);

    //             System.out.print("How many weeks is the booking for?: ");
    //             weekLengthStr = userEntry.nextLine();
    //             weekLength = validateInteger(weekLengthStr);

                
    //             //Here is where you update file with booked out dates would be like string bookedDates = bookedDates + " " + startDate + "-" + (weekLength * 7)
    //             // would need to check it hasnt already been booked for then (no overlapping bookings), then also what about attaching a user?
    //             // if it has been booked out already then say this, and give user the option to either propose new dates OR cancel booking and exit

    //             System.out.println("Property booked from " + startDate + " to " + startDate.plusWeeks(weekLength) + ". ");
    //             System.out.print("Do you currently have any additional upcoming bookings for this property? Y/N: ");
    //             upcoming = validateYesNo(userEntry.nextLine());

    //         } catch (Exception e) {
    //             // TODO: handle exception
    //         }
    //     }
    // }




}