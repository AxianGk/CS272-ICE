package IC03A.QUESTION1;

//import java.util.GregorianCalendar;
//import java.util.Calendar;

public class DriverLicense extends Card
{

     private int expYear;
     
     public DriverLicense(String n, int expYear)
     {
         super(n);
         this.expYear = expYear;
     }
     
     public String format()
     {
         return super.format() + "Exp year: " + expYear + "]";
     }
     
     public String toString()
     {
    	 return "DriverLicense" + super.toString() + " [expYear=" + expYear + "]";
     }
     
     public String equals(String n1, String n2, int expYear1, int expYear2)
     {
  	   String result = "[" + n1 + " and " + n2 + "are different]";
  	   if(n1 == n2)
  	   {
  		   if(expYear1 == expYear2)
  		   {
  			   result = "[" + n1 + " and " + n2 + "are the same]";
  		   }
  	   }
  	   return result;
     }

}
