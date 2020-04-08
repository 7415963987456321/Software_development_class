package FlightBooking;

interface Database {
    public Seat reserve(User user, Seat seat, String flightnumber);
    // Should the arguments be a single array or double?
    public Object[] search(String[] arguments);
}
