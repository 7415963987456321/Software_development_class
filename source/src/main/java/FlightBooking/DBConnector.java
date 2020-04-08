package FlightBooking;
import java.sql.*;
import java.util.*;

public class DBConnector implements Database{
    // Use a list instead of array
    List<Flight> flightList;
    List<Seat>   seatList;

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

            getSeats();
            getFlightList("LY389");

        } catch(SQLException e) {
            System.out.println("Error in DBConnector");
            System.err.println(e.getMessage());
        }finally {
            try { if (rs    != null) rs.close();    } catch (Exception e) {};
            try { if (stmt  != null) stmt.close();  } catch (Exception e) {};
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) {};
            try { if (conn  != null) conn.close();  } catch (Exception e) {};
        }
    }


    public Seat reserve(User user, Seat seat, String flightNumber ){
        try {
            pstmt = conn.prepareStatement("SELECT * FROM Seat WHERE flightnumber = ? AND reservation='NULL' ");
            pstmt.setString(1, flightNumber);
            pstmt.setString(2, flightNumber);
            ResultSet rs = pstmt.executeQuery();

        } catch(SQLException e) {
            System.out.println("Error in getting Flight");
            System.err.println(e.getMessage());
        }
        return seat;
    }

    public Object[] search(String[] arguments){
        // Search by flightname for now.
        List<Flight> results = getFlightList(arguments[0]);

        System.out.println("Search results: " + results);
        return new Object[10];
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
            System.out.println("Error in getting Flight");
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
            System.out.println("Error in getting Flight");
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
            // rs.get
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

                // Seat newSeat = new Seat(seatNumber, );

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
