package Software_development_class.source;
interface Database
{
  public Seat reserve(User user, Seat seat);
  public Object[] search(String[][] arguments);
}