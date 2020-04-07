package FlightBooking;

interface Database {
    public Seat reserve(User user, Seat seat);
    public Object[] search(String[][] arguments);
}
