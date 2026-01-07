package factory;
import model.RoomListing;
import java.time.*;
import java.util.*;

public class RoomListingFactory {

    public static RoomListing fromTextLine(String line) {
        String[] parts = line.split(" ");

        int id = Integer.parseInt(parts[0]);
        String homeowner = parts[1];
        String city = parts[2];
        String area = parts[3];
        double rent = Double.parseDouble(parts[4]);
        int mins = Integer.parseInt(parts[5]);
        
        List<String> amenities = List.of(parts[6].split("\\."));
        Map<LocalDate, Integer> unavailDates = formatDates(parts[7]);

        return new RoomListing(id, homeowner, city, area, rent, mins, amenities, unavailDates);
    }

    public static RoomListing fromHomeowner(int id, String homeowner, String city, String area, double rent, int mins, String userAmenities, String userDates) {
        // amenities in structure desk.bed.wardrobe, dates in structure 2025-10-5-21.2025-12-28-21
        List<String> amenities = List.of(userAmenities.split("\\."));
        Map<LocalDate, Integer> unavailDates = formatDates(userDates);

        return new RoomListing(id, homeowner, city, area, rent, mins, amenities, unavailDates);
    
    }

    private static Map<LocalDate, Integer> formatDates(String dates) {
        Map<LocalDate, Integer> unavailDates = new HashMap<>();

        if (!"none".equals(dates)) { // dates in structure of 2025-10-5-21.2025-12-28-21 or none

            for (String entry : dates.split("\\.")) { // seperates dates by . and loops through quantity of them
                String[] parts = entry.split("-");
                
                LocalDate startDate = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])); // YYYY-MM-DD
                int length = Integer.parseInt(parts[3]);
                unavailDates.put(startDate, length);

            }
        }
        
        return unavailDates;
    }

}
