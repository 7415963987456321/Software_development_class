package Test;

import FlightBooking.*;
import org.junit.*;


public class FlightControllerTest {
    public User user1;
    public Flight flight1;
    public Seat  seat1;
    public DBConnector db;

    @Before
    public void setUp(){
        try {
            db = new DBConnector();
        } catch(SQLException e) {
            System.out.println("Error in Flight test");
            System.err.println(e.getMessage());
        }
        
        User user1 = new User();
        user1.setName("tester");
        user1.setId(10);

        Flight flight1 = new Flight();
        flight1.setNumber(50);

        Seat seat1 = new Seat(flight1, 1,2,"Business");
        seat1.setNumber(100);
    }

    @After
    public void tearDown(){
        user1=null;
        flight1 =null;
        seat1 = null;
    }

    @Test
    public void testReservation(){
        FlightController testController = new FlightController(db);
        //testController.reserve(user1, flight1, seat1);
        //assertEquals(user1, seat1.getReservation());
    }
}
