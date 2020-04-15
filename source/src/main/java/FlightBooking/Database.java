package FlightBooking;
import java.util.*;
interface Database {
    public Seat reserve(User user, Seat seat, String flightnumber);
    // Should the arguments be a single array or double?
    public List<Seat> searchSeat(Flight flight);
    public List<Flight> searchFlight(String origin, String destination);
    public User getUser(int userId);
}
