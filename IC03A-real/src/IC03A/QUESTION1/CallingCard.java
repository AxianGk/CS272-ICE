package IC03A.QUESTION1;

public class CallingCard extends Card
{
    private String cardNumber;
    private String pin;
    
    public CallingCard(String n, String cardNumber, String pin)
    {
        super(n);
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
    
    public String format()
    {
       return super.format() + "Number: " + cardNumber + ", Pin: " + pin;
    }
    
    public String toString()
    {
    	return "Calling" + super.toString() + " [number=" + cardNumber + ",pin=" + pin;
    }
    
    public String equals(String n1, String n2, String cardNumber1, String cardNumber2, String pin1, String pin2)
    {
 	   String result = "[" + n1 + " and " + n2 + "are different]";
 	   if(n1 == n2)
 	   {
 		   if(cardNumber1 == cardNumber2)
 		   {
 			   if(pin1 == pin2)
 			   {
 				  result = "[" + n1 + " and " + n2 + "are the same]";
 			   }
 		   }
 	   }
 	   return result;
    }
    
    
}
