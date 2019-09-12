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
     

}
