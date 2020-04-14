package FlightBooking;
public class UserController {
   private Database database;
  
   // Constructor !REQUIRES A DATABASE!
   public UserController(Database controllerDatabase){
         database = controllerDatabase;
   }
}