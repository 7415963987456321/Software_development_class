package FlightBooking;

public class FlightController {
     private Database database;

     // Constructor !REQUIRES A DATABASE!
    public FlightController(Database controllerDatabase) {
         database = controllerDatabase;
    }

    public Seat reserve(User user, Flight flight, Seat seat) throws NullPointerException {
         Seat reserved;
         reserved = database.reserve(user, seat, flight.getNumber());

         if(reserved == null) {
             throw new NullPointerException("seat does not exist");
         }
         else if(reserved.getReservation() != user) {
             return null;
         }
         else {
             return reserved;
         }
     }
     /*public Object[] search(String[][]){

       }*/
 }

