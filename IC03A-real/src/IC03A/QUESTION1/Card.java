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
      return "[Name=" + name + "]";
   }
   
   public String equals(String n1, String n2)
   {
	   if(n1 == n2)
	   {
		   return "[" + n1 + " and " + n2 + "are the same]";
	   }
	   else
	   {
		   return "[" + n1 + " and " + n2 + "are different]";
	   }
   }
}
