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
    
    
    
    
}
