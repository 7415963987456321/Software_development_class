package Test;

import FlightBooking.*;
import org.junit.*;
import java.sql.*;


public class DatabaseTest {
    public DBConnector control;
    public User user1;
    public Flight flight1;
    public Seat  seat1;

    @Before
    public void setUp(){
        try {
            control = new DBConnector();
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
        control = null;
    }

    @Test
    public void testDatabase(){
        // Need to update this
        // Also need to figure out what the search result is supposed to look like.

        String[] arguments = {
            "VA57", "LA396"
        };

        control.search(arguments);
        // control.reserve(user1, seat1, flight1.getNumber());
    }
}
