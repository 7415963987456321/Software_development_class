package FlightBooking;
import java.util.*;

public class BookingApp{
  
  public static void main(String[] args){
  //initalizing all necassery parts
    try{
      List<Flight> flightList = null;
      List<Seat>   seatList = null;
      Database database = new DBConnector();
      FlightController fController = new FlightController(database);
      UserController uController = new UserController(database);
      Scanner reader = new Scanner(System.in);
      
      //ask username from user
      User user = null;
      while (user ==null){
        System.out.println("please give a id to log in with");
        int username = reader.nextInt();
        reader.nextLine();
        //login with given username
        user = uController.login(username);
      }
      System.out.println("Loged in");
      
      //main loop of search and reservation
      boolean quit = false;
      while(quit ==false)
      {
        System.out.print("please give command: ");
        String command = reader.nextLine();
        System.out.println(command);
        if(command.equals("quit")){
           quit =true;
        }
        
        else if(command.equals("search"))
        {
          System.out.println("please give destination");
          String dest = reader.nextLine();
          System.out.println("please give takeoff location");
          String origin = reader.nextLine();
          String[] arguments = new String[]{dest,origin};
          flightList = fController.flightSearch(arguments);
          if (flightList!=null){
            System.out.println("found " + flightList.size() + " flights");

            for(int i=0; i<flightList.size(); i++)
            {
              Flight flight = flightList.get(i); 
              System.out.print(i);
              System.out.print(" ");
              System.out.println(flight.getNumber());
            }
          }
        }
        
        else if(command.equals("reserve")&&flightList!=null)
        {
          System.out.println("please select flight ID in table");
          int flightIndex = reader.nextInt();
          reader.nextLine();
          seatList = fController.searchSeat(flightList.get(flightIndex));
          if (seatList.size()>1){
            System.out.println("advailable seats");
            for(int i=0; i<seatList.size(); i++)
            {
              Seat seat = seatList.get(i); 
              System.out.print(i +" ");
              System.out.print("number "+ seat.getNumber()+" ");
              System.out.println("price "+ seat.getPrice());
            }
            System.out.println("please select seat");
            int seatIndex = reader.nextInt();
            reader.nextLine();
            Seat reserved = fController.reserve(user, flightList.get(flightIndex),  seatList.get(seatIndex));
            if (reserved!=null){
              System.out.println("seat "+ reserved.getNumber() + " reserved");
            }
          }
          else{
            System.out.println("no advailable seats");
          }
        }
      }
    }
    //error handling
    catch(Exception e){
      System.out.println("there was an error" + e);      
    }
  }
}