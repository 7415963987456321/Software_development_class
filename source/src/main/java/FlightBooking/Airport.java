package FlightBooking;

public class Airport{
    private String location;
    private String accessability;


    // Setters and getters
    // Location
    public void setLocation(String airportLocation) {
        location = airportLocation;
    }

    public String getLocation() {
        return location;
    }

    // Accessability
    public void setAccessability(String airPortAccessabilty) {
        accessability = airPortAccessabilty;
    }

    public String getAccessability() {
        return accessability;
    }

}
