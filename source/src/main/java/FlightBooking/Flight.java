package FlightBooking;

public class Flight{
    private Airport start;
    private Airport end;
    private int takeoff;
    private int landing;
    private int number;
    private String type;
    private Company company;
    private String amenities;



    // Setters and getters
    // Start
    public void setStart(Airport flightStart) {
        start = flightStart;
    }

    public Airport getStart() {
        return end;
    }

    // End
    public void setEnd(Airport flightEnd) {
        end = flightEnd;
    }

    public Airport getEnd() {
        return end;
    }

    // Takeoff
    public void setTakeOff(int flightTakeOff) {
        takeoff = flightTakeOff;
    }

    public int getTakeOff() {
        return takeoff;
    }

    // Landing
    public void setLanding(int flightLanding) {
        landing = flightLanding;
    }

    public int getLanding() {
        return landing;
    }

    // Type
    public void setType(String flightType) {
        type = flightType;
    }

    public String getType() {
        return type;
    }

    // Number
    public void setNumber(int flightNumber) {
        number = flightNumber;
    }

    public int getNumber() {
        return number;
    }

    // Company
    public void setCompany(Company flightCompany) {
        company = flightCompany;
    }

    public Company getCompany() {
        return company;
    }

    // Amenities
    public void setAmenities(String flightAmenities) {
        amenities = flightAmenities;
    }

    public String getFlightAmenities() {
        return amenities;
    }

    /*
       public void setFlightSeatReserve(boolean flightSeatReserve) {
       this.flightSeatReserve = flightSeatReserve;
       }

       public boolean getFlightSeatReserve() {
       return flightSeatReserve;
       }
       */
}
