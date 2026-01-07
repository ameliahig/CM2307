package model;
import factory.RoomListingFactory;
import service.BookingService;

import java.time.*;
import java.util.*;

public class RoomListing {

    private int id;
    private String ownerUsername; 
    private String city;
    private String area;
    private double monthlyRent;
    private int distInMins;
    private List<String> amenities;
    private Map<LocalDate, Integer> unavailDates; //key value pair for startDate and duration (of weeks) of each booking

    public RoomListing(int id, String ownerUsername, String city, String area, double monthlyRent, int distInMins, List<String> amenities, Map<LocalDate, Integer> unavailDates) {
        this.id = id;
        this.ownerUsername = ownerUsername;
        this.city = city;
        this.area = area;
        this.monthlyRent = monthlyRent;
        this.distInMins = distInMins;
        this.amenities = amenities; //or = new ArrayList<>(amenities);
        this.unavailDates = unavailDates;
    }

    public boolean addBooking(LocalDate start, int weeks, BookingService bookingService) {
        if (bookingService.bookingOverlap(null, start, weeks) == true) {
            return false;
        } else {
            unavailDates.put(start, weeks);
            return true;
        }    
    }

    // public void addBooking(LocalDate start, int weeks) {
    //     unavailDates.put(start, weeks);
    // }

    // Getters (used by students)
    public int getID() {
        return id; 
    }
    public String getOwner() {
        return ownerUsername;
    }
    public String getCity() { 
        return city; 
    }
    public String getArea() { 
        return area; 
    }
    public double getMonthlyRent() { 
        return monthlyRent; 
    }
    public int getDist() {
        return distInMins;
    }
    public List<String> getAmenities() {
        return amenities;
    }
    public Map<LocalDate, Integer> getUnavailDates() {
        return unavailDates;
    }

}
    // public Map<LocalDate, Integer> getUnavailDates() {
    //     return unavailDates;
    // }



    // public fileToClass() {
    //     File listings = new File("listings.txt");
    //     try (Scanner fileReader = new Scanner(listings)) {
    //         while (fileReader.hasNextLine()) {
    //             //split the line by the spaces 
    //             String[] lineValues = fileReader.nextLine().split(" ");
    //             if (lineValues[0].equals(listingID)) { // if listing is found
    //                 lineValues[8]
    //             }

                
    //         }
    //     }
    // }

    // public datesAvail() {
    //     LocalDate leaseStart = LocalDate.of(2025, 10, 1); //year, month, day
    //     LocalDate leaseEnd = leaseStart.plusDays(10); //termLength
        

    // }

    