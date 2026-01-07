package service;
import model.*;
import java.util.*;
import java.io.*;

public class Session {
    private boolean active = true;
    private User currentUser;
    private List<RoomListing> listings;

    public Session(User currentUser, List<RoomListing> listings) {
        this.currentUser = currentUser;
        this.listings = listings;
    }

    public boolean isActive() {
        return active;
    }

    public void logout() {
        active = false;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    public List<RoomListing> getListings() {
        return listings;
    }

}

