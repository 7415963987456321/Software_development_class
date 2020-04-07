package FlightBooking;

public class Seat{
    private String seatClass;
    private int price;
    private int number;
    private User reservation;
    private Flight flight;


    //constructor
   public Seat(Flight seatFlight, int seatPrice, int seatNumber, String seatClass){
     setFlight(seatFlight);
     setPrice(seatPrice);
     setNumber(seatNumber);
     setSeatClass(seatClass);
   }


    // Setters and getters
    // SeatClass !!DO NOT CHANGE to getClass()!! can't override objects get class method
    public void setSeatClass(String seatClass) {
        seatClass = seatClass;
    }

    public String getSeatClass() {
        return seatClass;
    }

    // Price
    public void setPrice(int seatPrice) {
        price = seatPrice;
    }

    public int getPrice() {
        return price;
    }

    // Number
    public void setNumber(int seatNumber) {
        number = seatNumber;
    }

    public int getNumber() {
        return number;
    }

    // Reservation
    public void setReservation(User seatReservation) {
        reservation = seatReservation;
    }

    public User getReservation() {
        return reservation;
    }

    // Reservation
    public void setFlight(Flight seatFlight) {
        flight = seatFlight;
    }

    public Flight getFlight() {
        return flight;
    }

}
