package other;
import model.RoomListing;
import java.io.*;
import java.util.*;
import java.time.*;

public class ListingFileManagement {
    private String filePath = "../resources/listings.txt";

    public int getNextID() {
        // goes through listing file to see where the next unused number is

        int currentID = 0;
        try (Scanner fileReader = new Scanner(new File(filePath))) {

            while (fileReader.hasNextLine()) {
                String[] parts = fileReader.nextLine().split(" ");
                currentID = Integer.parseInt(parts[0]);
            }
            // returns next available number based on number of textlines 
            return (currentID + 1);

        } catch (Exception e) {
            System.out.println("Error reading listings file: " + e.getMessage());
            return currentID;
        }
    }

    public void saveAll(List<RoomListing> listings) {
        String textLine = "";
        try {
            FileWriter listingFile = new FileWriter(filePath, true);
            
            for (RoomListing listing : listings) {
                textLine = formatListing(listing);
                listingFile.write(textLine + "\n");  
            }
            
            listingFile.close();
        
        } catch (Exception e) {
            System.out.println("Error occurred when writing to file: " + e.getMessage());
        }  
    }

    private String formatListing(RoomListing listing) {
        // convert ID, rent, and minutes distance to strings
        String id = Integer.toString(listing.getID());
        String rent = Double.toString(listing.getMonthlyRent());
        String dist = Integer.toString(listing.getDist());

        // amenities and dates both need to be formatted before converting to string
        List<String> amenitiesList = listing.getAmenities();
        Map<LocalDate, Integer> unavailDatesMap = listing.getUnavailDates(); 
        
        // format list of amenities into one line
        String amenities = "";
        if (amenitiesList.size() == 0) {
            amenities = "none";
        } else {
            for (String item : amenitiesList) {
                amenities = amenities + "." + item;
            }
            amenities = amenities.replaceFirst(".", "");
        }

        // format map of booked out dates into one line
        String unavailDates = "";
        if (unavailDatesMap.size() == 0) {
            unavailDates = "none";
        } else {
            for (Map.Entry<LocalDate, Integer> entry : unavailDatesMap.entrySet()) {
                unavailDates = unavailDates + "." + entry.getKey().toString() + "-" + Integer.toString(entry.getValue());
            }
            unavailDates = unavailDates.replaceFirst(".", "");
        }

        String textLine = id + " " + listing.getOwner() + " " + listing.getCity() + " " + listing.getArea() + " " + rent + " " + dist + " " + amenities + " " + unavailDates;
        return textLine;

    }

    public void saveNewListing(RoomListing listing) {
        try {
            FileWriter listingFile = new FileWriter(filePath, true);
            String textLine = formatListing(listing);
            listingFile.write(textLine + "\n");  
            listingFile.close();

        } catch (Exception e) {
            System.out.println("Error occurred when writing to file: " + e.getMessage());
        }
    }

}