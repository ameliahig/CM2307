package service;
import model.RoomListing;
import model.User;
import model.Homeowner;
import other.ListingFileManagement;
import factory.RoomListingFactory;
import java.time.*;
import java.io.*;
import java.util.*;


public class ManageListing {
    private List<RoomListing> listings = new ArrayList<>();
    private ListingFileManagement fileManager;

    public ManageListing(ListingFileManagement fileManager, List<RoomListing> listings) {
        this.fileManager = fileManager;
        this.listings = listings;
    }

    public RoomListing setListing(Homeowner owner, String city, String area, double monthlyRent, int distInMins, List<String> amenities, Map<LocalDate, Integer> unavailDates) {
        int id = fileManager.getNextID();
        return new RoomListing(id, owner.getUsername(), city, area, monthlyRent, distInMins, amenities, unavailDates);
    }

    public RoomListing createListing(Homeowner owner) {
        Scanner userEntry = new Scanner(System.in);  
        boolean exit = false; 
        String city = ""; double rent = 0; String area = ""; int dist = 0; 
        List<String> amenities = new ArrayList<>(); Map<LocalDate, Integer> bookedDates = new HashMap<>();
            
        while (exit == false) {
            amenities.clear();
            bookedDates.clear();

            System.out.println("Please fill in the following details about the available room: ");

            city = readCity(userEntry);
            rent = readRent(userEntry);
            area = readArea(userEntry, city);
            dist = readDist(userEntry);
            amenities = readAmenities(userEntry);
            //Because its a new listing there are initially no bookings
            exit = confirmListing(userEntry, area, rent, dist, city, amenities);

        }
        return setListing(owner, city, area, rent, dist, amenities, bookedDates);
    }

    public void saveNew(RoomListing listing) {
        fileManager.saveNewListing(listing);
    }
    

    private String readCity(Scanner userEntry) {
        System.out.print("University city location: ");
        String city = userEntry.nextLine();
        while (true) {
            try {
                city = city.replaceAll("[^a-zA-Z]", "");
                city = city.substring(0, 1).toUpperCase() + city.substring(1);
                
                if (city.length() <= 3) {
                    throw new IllegalArgumentException("Term must be longer than 3 characters");
                }
                return city;

            } catch (Exception e) {
                System.out.println("Invalid term entered. '" + e.getMessage() + "'. Please try again: ");
                city = userEntry.nextLine();
            }
        }
    }

    private String readArea(Scanner userEntry, String city) {
        System.out.print("Area of " + city + ": ");
        String area = userEntry.nextLine();
        while (true) {
            try {
                area = area.replaceAll("[^a-zA-Z]", "");
                area = area.substring(0, 1).toUpperCase() + area.substring(1);
                
                if (area.length() <= 3) {
                    throw new IllegalArgumentException("Term must be longer than 3 characters");
                }
                return area;

            } catch (Exception e) {
                System.out.println("Invalid term entered. '" + e.getMessage() + "'. Please try again: ");
                city = userEntry.nextLine();
            }
        }
    }

    private double readRent(Scanner userEntry) {
        System.out.print("Monthly Rent: ");
        String rent = userEntry.nextLine();
        while (true) {
            try {
                if (Double.parseDouble(rent) <= 0) {
                    throw new IllegalArgumentException("Value must be larger than 0");
                }
                return Double.parseDouble(rent);
            } catch (Exception e) {
                System.out.print("Invalid decimal. '" + e.getMessage() + "'. Try again: ");
                rent = userEntry.nextLine();
            }   
        }  
    }

    private int readDist(Scanner userEntry) {
        System.out.print("Mins walk from campus: ");
        String dist = userEntry.nextLine();
        while (true) {
            try {
                if (Integer.parseInt(dist) <= 0) {
                    throw new IllegalArgumentException("Value must be larger than 0");
                }
                return Integer.parseInt(dist);
            } catch (Exception e) {
                System.out.print("Invalid number. '" + e.getMessage() + "'. Try again: ");
                dist = userEntry.nextLine();
            }   
        } 
    }

    private List<String> readAmenities(Scanner userEntry) {
        boolean infoBool; 
        List<String> amenities = new ArrayList<>();

        amenities.clear();
            
        System.out.println("Does the listing include an ensuite bathroom? Y/N: ");
        infoBool = Validator.validateYesNo(userEntry.nextLine());
        if (infoBool == true) {
            amenities.add("ensuite");
        }
        System.out.println("Does the listing include wifi? Y/N: ");
        infoBool = Validator.validateYesNo(userEntry.nextLine());
        if (infoBool == true) {
            amenities.add("wifi");
        }

        System.out.println("Is the listing fully furnished (bed, desk and wardrobe included)? Y/N: ");
        infoBool = Validator.validateYesNo(userEntry.nextLine());
        if (infoBool == true) {
            amenities.add("bed");
            amenities.add("desk");
            amenities.add("wardrobe");
        } else {
            System.out.println("Is the listing partly furnished (at least one of a bed, desk or wardrobe)? Y/N: ");
            infoBool = Validator.validateYesNo(userEntry.nextLine());
            if (infoBool == true) {
                System.out.println("Does the listing include a bed? Y/N: ");
                infoBool = Validator.validateYesNo(userEntry.nextLine());
                if (infoBool == true) {
                    amenities.add("bed");
                }
                System.out.println("Does the listing include a desk? Y/N: ");
                infoBool = Validator.validateYesNo(userEntry.nextLine());
                if (infoBool == true) {
                    amenities.add("desk");
                }
                System.out.println("Does the listing include a wardrobe? Y/N: ");
                infoBool = Validator.validateYesNo(userEntry.nextLine());
                if (infoBool == true) {
                    amenities.add("wardrobe");
                }
            }
        }
        return amenities;

    }

    private boolean confirmListing(Scanner userEntry, String area, double rent, int distInMins, String city, List<String> amenities) {
        System.out.println(" ");
        System.out.println("Room in " + area + " for £" + rent + " per month. " + distInMins + " mins walk from " + city + " campus.");
        System.out.println("Amenities: " + String.join(", ", amenities));
        System.out.print("Please confirm the above details are correct by typing Y/N: ");
        boolean exit = Validator.validateYesNo(userEntry.nextLine());
        return exit;
    }


}