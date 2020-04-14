package FlightBooking;

public class BookingApp{
  
  public static void main(String[] args){
  //initalizing all necassery parts
    try{
      Flight[] flightArray = new Flight[25];
      Database database = new DBConnector();
      FlightController fController = new FlightController(database);
      UserController uController = new UserController(database);
    }
    catch(Exception e){
      System.out.println("there was an error with database" + e);      
    }
  }
}