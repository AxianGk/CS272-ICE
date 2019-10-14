package IC03A.QUESTION1;

public class IDCard extends Card
{
        private String idNumber;
        
        public IDCard(String n, String id)
        {
            super(n);
            idNumber = id;
        }
        
        public String format()
        {
            return super.format() + "ID Number: " + idNumber;
        }
        
        public String toString()
        {
        	return "ID" + super.toString() + " [idnumber=" + idNumber + "]"; 
        }
        
        public String equals(String n1, String n2, String id1, String id2)
        {
        	String result = "[" + n1 + " and " + n2 + "are different]";
     	    if(n1.equals(n2))
     	    {
     		   if(id1 == id2)
     		   {
     			   result = "[" + n1 + " and " + n2 + "are the same]";
     		   }
     	    }
     	    return result;
        }

}
