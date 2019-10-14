package IC03A.QUESTION1;

public class BillFoldtester
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        DriverLicense d = new DriverLicense("John Doe", 2007);
        CallingCard c = new CallingCard("Card", "0123456789", "1234");
        
        BillFold b = new BillFold();
        
        b.addCard(d);
        b.addCard(c);
        
        System.out.println(b.formatCards());
    }

}
