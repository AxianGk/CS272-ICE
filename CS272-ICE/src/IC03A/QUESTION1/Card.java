package IC03A.QUESTION1;
/**
 * 
 * @author xzhao4
 * @version 9/9/2019
 *
 */

import java.util.GregorianCalendar;
import java.util.Calendar;

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
	  GregorianCalendar calendar = new GregorianCalendar();
	  calendar.get(Calendar.YEAR);
      return false;
   }

   public String format()
   {
      return "Name: " + name;
   }
   
   public String toString()
   {
	   return "Card[name=" + name + "]";
   }
   
   public String equals(String n1, String n2)
   {
	   String result = "[" + n1 + " and " + n2 + "are different]";
	   if(n1 == n2)
	   {
		   result = "[" + n1 + " and " + n2 + "are the same]";
	   }
	   return result;
   }
}
