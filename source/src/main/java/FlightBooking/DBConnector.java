package FlightBooking;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DBConnector implements Database{
    // Use a list instead of array
    List<Flight> flightList;
    List<Seat>   seatList;
    List<Flight> resultList;
    List<Seat> resultListSeat;

    // SQL variables
    static Connection        conn;
    static Statement         stmt;
    static PreparedStatement pstmt;
    static ResultSet         rs;

    public DBConnector() throws SQLException {
        // Connect to SQL database:
        try {
            // Load the sqlite-JDBC driver using the current class loader
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
            stmt = conn.createStatement();

            // For testing remove later:
            // getSeats();
            // getFlightList("LY389");
            // System.out.println("Search test: " + flightList.size());

        } catch(SQLException e) {
            System.out.println("Error in DBConnector");
            System.err.println(e.getMessage());
        }
    }

    public static void cleanup () {
        try { if (rs    != null) rs.close();    } catch (Exception e) {};
        try { if (stmt  != null) stmt.close();  } catch (Exception e) {};
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {};
        try { if (conn  != null) conn.close();  } catch (Exception e) {};
    }


    public Seat reserve(User user, Seat seat, String flightNumber ){
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Seat WHERE flightnumber = ? AND reservation='NULL' ");
            pstmt.setString(1, flightNumber);
            pstmt.setString(2, flightNumber);
            ResultSet rs = pstmt.executeQuery();

        } catch(SQLException e) {
            System.out.println("Error in reserve");
            System.err.println(e.getMessage());
        }
        return seat;
    }

    public List<Flight> searchFlight(String origin, String destination, Date takeoff){
        // A list of Flights:
        resultList = new ArrayList<Flight>();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM Flight WHERE origin=? AND dest=?");
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Flight newFlight = new Flight();


                newFlight.setNumber(rs.getString("number"));
                newFlight.setTakeOff(rs.getDate("takeoff"));
                newFlight.setLanding(rs.getDate("landing"));
                newFlight.setStart(getAirport(rs.getString("origin")));
                newFlight.setEnd(getAirport(rs.getString("dest")));
                newFlight.setType(rs.getString("aircraft"));
                newFlight.setCompany(getCompany(rs.getString("compname")));
                newFlight.setAmenities(rs.getString("amenities"));

                resultList.add(newFlight);
            }

        } catch(SQLException e) {
            System.out.println("Error in searchFlight");
            System.err.println(e.getMessage());
        }
        return resultList;
    }

     public List<Seat> searchSeat(Flight flight){
        // A list of seats:
        resultListSeat = new ArrayList<Seat>();

            int seatTableIndex = 0;
            String  flightNumber, flightClass, seatReservation;
            int seatPrice, seatNumber;

        try {
            pstmt = conn.prepareStatement("SELECT * FROM Seat WHERE flightnumber=?");
            pstmt.setString(1, flight.getNumber());

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                // For debugging, remove later
                System.out.println("Seatnumber = " + rs.getString("seatnumber"));

                seatNumber      = rs.getInt("seatnumber");
                flightNumber    = rs.getString("flightnumber");
                flightClass     = rs.getString("class");
                seatPrice       = rs.getInt("price");
                seatReservation = rs.getString("reservation");

                Seat newSeat = new Seat(flight, seatPrice, seatNumber, flightClass);
                newSeat.setReservation(null);

                resultListSeat.add(newSeat);
            }

        } catch(SQLException e) {
            System.out.println("Error in searchFlight");
            System.err.println(e.getMessage());
        }
        return resultListSeat;

     }

    public Object[] search(String[] arguments){
        // Search by flightname for now.
        // What are the search results supposed to look like?
        String test = arguments[0];
        List<Flight> results = getFlightList(test);

        System.out.println("Search arguments: " + arguments[0]);
        System.out.println("Search results: " + flightList.size());

        return new Object[10];
    }

    public static void deleteUser(int userid){
        try {
            // Delete user by userId:
            pstmt = conn.prepareStatement("DELETE FROM Customer WHERE userid=?");
            pstmt.setInt(1, userid);
            pstmt.executeUpdate();

            pstmt.close();

        } catch(SQLException e) {
            System.out.println("Error in deleting user");
            System.err.println(e.getMessage());
        }
    }

    // Returns true if new user successfully created, otherwise false.
    public static Boolean createNewUser(int userid, String username){
        User newUser = new User();
        try {
            // First check if the user exists:
            pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE userid=?");
            pstmt.setInt(1, userid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("User already exists!");
                return false;
            }

            pstmt = conn.prepareStatement("INSERT INTO Customer VALUES(?,?)");
            pstmt.setString(1, username);
            pstmt.setInt(2, userid);
            pstmt.executeUpdate();

        } catch(SQLException e) {
            System.out.println("Error in creating user");
            System.err.println(e.getMessage());
        }
            return true;
    }

    public static User getUser(int userId) {
        User newUser = new User();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE userid=?");
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            newUser.setName(rs.getString("username"));
            newUser.setId(rs.getInt("userid"));

        } catch(SQLException e) {
            System.out.println("Error in getting Customer");
            System.err.println(e.getMessage());
        }
        return newUser;
    }

    private Company getCompany(String companyName){
        Company newCompany = new Company();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Company WHERE compname=?");
            pstmt.setString(1, companyName);
            ResultSet rs = pstmt.executeQuery();

            newCompany.setName(rs.getString("compname"));
            newCompany.setId(rs.getInt("compid"));


        } catch(SQLException e) {
            System.out.println("Error in getting Company");
            System.err.println(e.getMessage());
        }
        return newCompany;
    }



    private Airport getAirport(String Airportname){
        Airport newAirport = new Airport();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM Airport WHERE airportname=?");
            pstmt.setString(1, Airportname);
            ResultSet rs = pstmt.executeQuery();

            newAirport.setLocation(rs.getString("airportname"));
            newAirport.setAccessibility(rs.getString("airportname"));

        } catch(SQLException e) {
            System.out.println("Error in getting Airport");
            System.err.println(e.getMessage());
        }
        return newAirport;
    }


    // Get a single flight, or should it be an array of all matching flights?
    private List<Flight> getFlightList(String flightNumber){
        // A list of Flights:
        flightList = new ArrayList<Flight>();

        try {
            pstmt = conn.prepareStatement("SELECT * FROM Flight WHERE number=?");
            pstmt.setString(1, flightNumber);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Flight newFlight = new Flight();

                newFlight.setTakeOff(rs.getDate("takeoff"));
                newFlight.setLanding(rs.getDate("landing"));
                newFlight.setStart(getAirport(rs.getString("origin")));
                newFlight.setEnd(getAirport(rs.getString("dest")));
                newFlight.setType(rs.getString("aircraft"));
                newFlight.setCompany(getCompany(rs.getString("compname")));
                newFlight.setAmenities(rs.getString("amenities"));

                flightList.add(newFlight);
            }
        } catch(SQLException e) {
            System.out.println("Error in getting Flightlist");
            System.err.println(e.getMessage());
        }
        return flightList;
    }

    // Fills the seatTable
    private void getSeats() {
        // A list of seats:
        seatList = new ArrayList<Seat>();
        try {
            int seatTableIndex = 0;
            String  flightNumber, flightClass, seatReservation;
            int seatPrice, seatNumber;

            ResultSet rs = stmt.executeQuery("SELECT * FROM Seat");

            while(rs.next()) {
                // For debugging, remove later

                System.out.println("Seatnumber = " + rs.getString("seatnumber"));

                seatNumber      = rs.getInt("seatnumber");
                flightNumber    = rs.getString("flightnumber");
                flightClass     = rs.getString("class");
                seatPrice       = rs.getInt("price");
                seatReservation = rs.getString("reservation");

                //Seat newSeat = new Seat();

                // Seat = (Flight seatFlight, int seatPrice, int seatNumber, String seatClass)
                // seatTable[seatTableIndex] = new Seat(getFlight("LY389"), seatPrice, seatNumber,flightClass);
                seatTableIndex++;
            }
        } catch(SQLException e) {
            System.out.println("Error in getting Seats");
            System.err.println(e.getMessage());
        } 
    }
}
