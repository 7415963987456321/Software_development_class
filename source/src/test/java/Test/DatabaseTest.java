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
        // flight1.setNumber(50);

        Seat seat1 = new Seat(flight1, 1,2,"Business");
        // seat1.setNumber(100);
    }

    @After
    public void tearDown(){
        user1   = null;
        flight1 = null;
        seat1   = null;
        control = null;
    }

    @Test
    public void testDatabase(){
        // Need to update this
        // Also need to figure out what the search result is supposed to look like.

        String[] arguments = {
             "MM502"
        };

        control.search(arguments);
        // control.reserve(user1, seat1, flight1.getNumber());

        // Test users:
        System.out.println("Attempt to get existing user:");
        user1 = control.getUser(0);
        System.out.println("Username: " + user1.getName());
        System.out.println("UserId: " + user1.getId());

        // Try to create new user that already exists:
        System.out.println("Attempt to create existing user:");
        Boolean tryExistingUser = control.createNewUser(0, "Arttu");
        System.out.println("Existing user : " + tryExistingUser);

        // Try to create new user that doesn't exist:
        System.out.println("Attempt to create new user:");
        Boolean tryNewUser = control.createNewUser(42, "Test");
        if(tryNewUser) System.out.println("New user created sucessfully: " + tryNewUser);

        // Try to delete the test user:
        // System.out.println("Attempt to delete the testuser:");
        // control.deleteUser(42);
        // User deletedUser = control.getUser(42);
        // System.out.println("Username: " + user1.getName());


    }
}
