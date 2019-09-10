package IC03A.QUESTION1;
/**
 * 
 * @author xzhao4
 * @version 9/9/2019
 *
 */

public class Card
{
   private String name;

   public Card()
   {
      name = "";
   }

   public Card(String n)
   {
      name = n;
   }

   public String getName()
   {
      return name;
   }

   public boolean isExpired()
   {
      return false;
   }

   public String format()
   {
      return "Card holder: " + name;
   }
}
