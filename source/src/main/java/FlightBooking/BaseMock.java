package FlightBooking;

public class BaseMock implements Database{
    private Flight[] flightTable;
    private Seat[] seatTable;

    public BaseMock(){
        //initalize mock database
        //in reality we would call a SQL database

        //initalizing a flight table(just one since we are testing seat reservation.
        Flight testFlight = new Flight();
        testFlight.setNumber(19);
        flightTable= new Flight[]{
            testFlight
        };

        //initalising seat table
        seatTable = new Seat[]{
            new Seat(flightTable[0], 100, 10, "Economy"),
                new Seat(flightTable[0], 100, 11, "Economy"),
                new Seat(flightTable[0], 500, 1, "Business"),
        };
        //making one user and reserving a seat to him
        User testUser = new User();
        testUser.setName("joe");
        testUser.setId(10);
    }


    public Seat reserve(User user, Seat seat){
        for (int i = 0; i < seatTable.length; ++i){
            if(seatTable[i].getNumber() == seat.getNumber()){
                if (seatTable[i].getReservation() == null){
                    seatTable[i].setReservation(user);
                }
                return seatTable[i];
            }
        }
        return null;
    }

    public Object[] search(String[][] arguments){
        return new Object[10];
    }
}
