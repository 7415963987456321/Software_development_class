package FlightBooking;

public class FlightController {
    private Database database;

    public Seat reserve(User user, Seat seat) throws NullPointerException {
        Seat reserved;
        reserved = database.reserve(user, seat);

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


    //constructor !REQUIERS A DATABASE!
    public FlightController(Database controllerDatabase)
    {
        database = controllerDatabase;
    }
}

